package org.microservice1.movie.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.microservice1.movie.client.AuthenticationServiceClient;
import org.microservice1.movie.entity.Movie;
import org.microservice1.movie.exception.MovieNotFoundException;
import org.microservice1.movie.exception.UnauthorizedAccessException;
import org.microservice1.movie.repository.MovieRepo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepo movieRepo;

    @Mock
    private AuthenticationServiceClient authenticationServiceClient;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testGetAllMoviesSuccess() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        String validToken = "validToken";
        Page<Movie> expectedPage = new PageImpl<>(Collections.emptyList());
        when(authenticationServiceClient.validateToken(anyString())).thenReturn(ResponseEntity.ok("OK"));
        when(movieRepo.findAll(pageable)).thenReturn(expectedPage);

        Page<Movie> result = movieService.getAllMovies(pageable, validToken);

        assertNotNull(result);
        assertEquals(expectedPage, result);
        verify(authenticationServiceClient, times(1)).validateToken(validToken);
        verify(movieRepo, times(1)).findAll(pageable);
    }

    @Test
    void testGetAllMoviesUnauthorized() {
        Pageable pageable = Pageable.unpaged();
        String invalidToken = "invalidToken";
        when(authenticationServiceClient.validateToken(anyString())).thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized"));

        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class,
                () -> movieService.getAllMovies(pageable, invalidToken));
        assertEquals("Unauthorized access", exception.getMessage());
        verify(authenticationServiceClient, times(1)).validateToken(invalidToken);
        verify(movieRepo, never()).findAll(pageable);
    }

    @Test
    void testGetMovieByIdSuccess() {
        Integer movieId = 1;
        String validToken = "validToken";
        Movie expectedMovie = new Movie();
        when(authenticationServiceClient.validateToken(anyString())).thenReturn(ResponseEntity.ok("OK"));
        when(movieRepo.findById(movieId)).thenReturn(Optional.of(expectedMovie));

        Movie result = movieService.getMovieById(movieId, validToken);

        assertNotNull(result);
        assertEquals(expectedMovie, result);
        verify(authenticationServiceClient, times(1)).validateToken(validToken);
        verify(movieRepo, times(1)).findById(movieId);
    }

    @Test
    void testGetMovieByIdUnauthorized() {
        Integer movieId = 1;
        String invalidToken = "invalidToken";
        when(authenticationServiceClient.validateToken(anyString())).thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized"));

        UnauthorizedAccessException exception = assertThrows(UnauthorizedAccessException.class,
                () -> movieService.getMovieById(movieId, invalidToken));
        assertEquals("Unauthorized access", exception.getMessage());
        verify(authenticationServiceClient, times(1)).validateToken(invalidToken);
        verify(movieRepo, never()).findById(movieId);
    }

    @Test
    void testGetMovieByIdNotFound() {
        Integer movieId = 1;
        String validToken = "validToken";
        when(authenticationServiceClient.validateToken(anyString())).thenReturn(ResponseEntity.ok("OK"));
        when(movieRepo.findById(movieId)).thenReturn(Optional.empty());

        MovieNotFoundException exception = assertThrows(MovieNotFoundException.class,
                () -> movieService.getMovieById(movieId, validToken));
        assertEquals("Movie not found with ID: 1", exception.getMessage());
        verify(authenticationServiceClient, times(1)).validateToken(validToken);
        verify(movieRepo, times(1)).findById(movieId);
    }

    @Test
    void testGetAllMoviesWithoutTokenForLanding() {
        List<Movie> expectedMovies = Collections.emptyList();
        when(movieRepo.findAll()).thenReturn(expectedMovies);

        List<Movie> result = movieService.getAllMoviesWithoutTokenForLanding();

        // Assert
        assertNotNull(result);
        assertEquals(expectedMovies, result);
        verify(movieRepo, times(1)).findAll();
        verify(authenticationServiceClient, never()).validateToken(anyString());
    }
}
