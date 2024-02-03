package org.microservice1.movie.controller;

import lombok.RequiredArgsConstructor;
import org.microservice1.movie.entity.Movie;
import org.microservice1.movie.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "18") int size,
            @RequestHeader("Authorization") String authorizationHeader) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Movie> movies = movieService.getAllMovies(pageable, authorizationHeader);
        return ResponseEntity.ok(movies.getContent());
    }
    @GetMapping("/landing")
    public ResponseEntity<List<Movie>> getMoviesWithoutToken() {

        List<Movie> movies = movieService.getAllMoviesWithoutTokenForLanding();
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id, @RequestHeader("Authorization") String authorizationHeader) {
        Movie movie = movieService.getMovieById(id, authorizationHeader);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
