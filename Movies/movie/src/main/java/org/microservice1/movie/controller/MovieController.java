package org.microservice1.movie.controller;

import lombok.RequiredArgsConstructor;
import org.microservice1.movie.entity.Movie;
import org.microservice1.movie.exception.MovieNotFoundException;
import org.microservice1.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<?> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size,
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            Page<Movie> movies = movieService.getAllMovies(pageable, authorizationHeader);
            return ResponseEntity.ok(movies.getContent());
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Unauthorized access");
        }
    }

    @GetMapping("/landing")
    public ResponseEntity<List<Movie>> getMoviesWithoutToken() {
        List<Movie> movies = movieService.getAllMoviesWithoutTokenForLanding();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            Movie movie = movieService.getMovieById(id, authorizationHeader);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Unauthorized access");
        }
    }
}
