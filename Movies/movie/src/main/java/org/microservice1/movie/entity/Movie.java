package org.microservice1.movie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    private Integer id;
    private Boolean adult;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    @Column(length = 400000)
    private String overview;
    private Double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private Boolean video;
    private Double vote_average;
    private Integer vote_count;
}