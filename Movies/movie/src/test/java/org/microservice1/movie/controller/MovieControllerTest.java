package org.microservice1.movie.controller;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.microservice1.movie.exception.UnauthorizedAccessException;
import org.microservice1.movie.service.MovieService;
import org.mockito.Mockito;
import org.microservice1.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8081)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @BeforeEach
    void setup() {
        WireMock.reset();
    }

    @Test
    void testGetMoviesWithMockedAuthentication() throws Exception {
        // Set up WireMock to mock the validateToken endpoint
        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("OK")));

        List<Movie> mockMovies = createMockMovies();
        Page<Movie> mockPage = new PageImpl<>(mockMovies);
        Mockito.when(movieService.getAllMovies(Mockito.any(), Mockito.anyString())).thenReturn(mockPage);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                        .param("page", "0")
                        .param("size", "1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(mockMovies.get(0).getTitle()))
                .andExpect(jsonPath("$[0].overview").value(mockMovies.get(0).getOverview()));

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("JSON Response: " + jsonResponse);
    }

    @Test
    void testGetMoviesWithoutTokenForLanding() throws Exception {
        List<Movie> mockMovies = createMockMovies();
        Mockito.when(movieService.getAllMoviesWithoutTokenForLanding()).thenReturn(mockMovies);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/landing")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(mockMovies.size())))
                .andExpect(jsonPath("$[0].title").value(mockMovies.get(0).getTitle()))
                .andExpect(jsonPath("$[1].title").value(mockMovies.get(1).getTitle()));
    }

    @Test
    void testGetMovieByIdWithMockedAuthentication() throws Exception {
        int movieId = 1;

        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("OK")));

        Movie mockMovie = createMockMovies().get(0);
        Mockito.when(movieService.getMovieById(movieId, "Bearer testToken")).thenReturn(mockMovie);

        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", movieId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(mockMovie.getTitle()))
                .andExpect(jsonPath("$.overview").value(mockMovie.getOverview()));
    }

    @Test
    void testGetMoviesUnauthorized() throws Exception {
        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
                .willReturn(WireMock.aResponse()
                        .withStatus(403)
                        .withBody("Unauthorized access")));

        Mockito.when(movieService.getAllMovies(Mockito.any(), Mockito.anyString()))
                .thenThrow(new UnauthorizedAccessException("Unauthorized access"));

        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                        .param("page", "0")
                        .param("size", "10")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mockToken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Unauthorized access"));
    }

    @Test
    void testGetMovieByIdUnauthorized() throws Exception {
        int movieId = 1;

        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
                .willReturn(WireMock.aResponse()
                        .withStatus(403)
                        .withBody("Unauthorized access")));

        Mockito.when(movieService.getMovieById(Mockito.eq(movieId), Mockito.anyString()))
                .thenThrow(new UnauthorizedAccessException("Unauthorized access"));

        // Perform the request without a valid token for a specific movie and expect a 403 Forbidden response
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", movieId)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer mockToken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Unauthorized access"));
    }

    private List<Movie> createMockMovies() {
        return Arrays.asList(
                Movie.builder()
                        .id(278)
                        .adult(false)
                        .backdrop_path("/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg")
                        .original_language("en")
                        .original_title("The Shawshank Redemption")
                        .overview("Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.")
                        .popularity(115.382)
                        .poster_path("/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg")
                        .release_date("1994-09-23")
                        .title("The Shawshank Redemption")
                        .video(false)
                        .vote_average(8.711)
                        .vote_count(25452)
                        .build(),
                Movie.builder()
                        .id(238)
                        .adult(false)
                        .backdrop_path("/rSPw7tgCH9c6NqICZef4kZjFOQ5.jpg")
                        .original_language("en")
                        .original_title("The Godfather")
                        .overview("Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.")
                        .popularity(119.85)
                        .poster_path("/3bhkrj58Vtu7enYsRolD1fZdja1.jpg")
                        .release_date("1972-03-14")
                        .title("The Godfather")
                        .video(false)
                        .vote_average(8.708)
                        .vote_count(19372)
                        .build()
        );
    }
}