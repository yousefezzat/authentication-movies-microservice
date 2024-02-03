package org.microservice1.movie.repository;

import org.microservice1.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
}
