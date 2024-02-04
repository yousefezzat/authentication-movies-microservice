package org.microservice1.movie.service;

import lombok.RequiredArgsConstructor;
import org.microservice1.movie.entity.Movie;
import org.microservice1.movie.client.AuthenticationServiceClient;
import org.microservice1.movie.exception.MovieNotFoundException;
import org.microservice1.movie.exception.UnauthorizedAccessException;
import org.microservice1.movie.repository.MovieRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepo movieRepository;
    private final AuthenticationServiceClient authenticationServiceClient;

    public Page<Movie> getAllMovies(Pageable pageable, String authorizationHeader) {
        // Validate token before making the request
        ResponseEntity<String> response = validateToken(authorizationHeader);
        if (response.getStatusCode().is2xxSuccessful()) {
            return movieRepository.findAll(pageable);
        }
        else
        {
            throw new UnauthorizedAccessException("Unauthorized access");
        }

    }

    public List<Movie> getAllMoviesWithoutTokenForLanding() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id, String authorizationHeader) {
        ResponseEntity<String> response = validateToken(authorizationHeader);
        if (response.getStatusCode().is2xxSuccessful()) {
            return movieRepository.findById(id)
                    .orElseThrow(() -> new MovieNotFoundException("Movie not found with ID: " + id));        }
        else
        {
            throw new UnauthorizedAccessException("Unauthorized access");
        }


    }

    private ResponseEntity<String> validateToken(String authorizationHeader) {
        // Call the Authentication microservice to validate the token
        return authenticationServiceClient.validateToken(authorizationHeader);

    }
}
