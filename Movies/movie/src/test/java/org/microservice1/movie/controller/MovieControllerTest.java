package org.microservice1.movie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microservice1.movie.entity.Movie;
import org.microservice1.movie.exception.MovieNotFoundException;
import org.microservice1.movie.exception.UnauthorizedAccessException;
import org.microservice1.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    @Test
    void testGetMovies() throws Exception {
        int page = 0;
        int size = 18;
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        List<Movie> movies = Collections.singletonList(new Movie());
        Page<Movie> moviePage = new PageImpl<>(movies);

        when(movieService.getAllMovies(pageable, "validToken")).thenReturn(moviePage);

        mockMvc.perform(get("/movies")
                        .header(HttpHeaders.AUTHORIZATION, "validToken")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists());

        verify(movieService, times(1)).getAllMovies(pageable, "validToken");
    }

    @Test
    void testGetMoviesWithoutToken() throws Exception {
        List<Movie> movies = Collections.singletonList(new Movie());

        when(movieService.getAllMoviesWithoutTokenForLanding()).thenReturn(movies);

        mockMvc.perform(get("/movies/landing"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").exists());

        verify(movieService, times(1)).getAllMoviesWithoutTokenForLanding();
    }

    @Test
    void testGetMovieById() throws Exception {
        Integer movieId = 1;
        Movie movie = new Movie();
        when(movieService.getMovieById(movieId, "validToken")).thenReturn(movie);

        mockMvc.perform(get("/movies/{id}", movieId)
                        .header(HttpHeaders.AUTHORIZATION, "validToken"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());

        verify(movieService, times(1)).getMovieById(movieId, "validToken");
    }

    @Test
    void testGetMovieByIdNotFound() throws Exception {
        Integer movieId = 1;
        when(movieService.getMovieById(movieId, "validToken")).thenThrow(new MovieNotFoundException("Movie not found"));

        mockMvc.perform(get("/movies/{id}", movieId)
                        .header(HttpHeaders.AUTHORIZATION, "validToken"))
                .andExpect(status().isNotFound());

        verify(movieService, times(1)).getMovieById(movieId, "validToken");
    }

    @Test
    void testGetMoviesUnauthorized() throws Exception {
        when(movieService.getAllMovies(any(), anyString())).thenThrow(new UnauthorizedAccessException("Unauthorized access"));

        mockMvc.perform(get("/movies")
                        .header(HttpHeaders.AUTHORIZATION, "invalidToken"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Unauthorized access"));

        verify(movieService, times(1)).getAllMovies(any(), anyString());
    }

    @Test
    void testGetMovieByIdUnauthorized() throws Exception {
        Integer movieId = 1;
        when(movieService.getMovieById(movieId, "invalidToken")).thenThrow(new UnauthorizedAccessException("Unauthorized access"));

        mockMvc.perform(get("/movies/{id}", movieId)
                        .header(HttpHeaders.AUTHORIZATION, "invalidToken"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Unauthorized access"));

        verify(movieService, times(1)).getMovieById(movieId, "invalidToken");
    }
}
