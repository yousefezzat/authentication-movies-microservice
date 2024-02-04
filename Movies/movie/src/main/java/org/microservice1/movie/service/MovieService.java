package org.microservice1.movie.service;

import lombok.RequiredArgsConstructor;
import org.microservice1.movie.entity.Movie;
import org.microservice1.movie.client.AuthenticationServiceClient;

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
        validateToken(authorizationHeader);

        return movieRepository.findAll(pageable);
    }
    public List<Movie> getAllMoviesWithoutTokenForLanding() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Integer id, String authorizationHeader) {
        validateToken(authorizationHeader);

        return movieRepository.findById(id).orElse(null);
    }

    public ResponseEntity<String> validateToken(String authorizationHeader) {
        // Call the Authentication microservice to validate the token
        return authenticationServiceClient.validateToken(authorizationHeader);


    }
}

