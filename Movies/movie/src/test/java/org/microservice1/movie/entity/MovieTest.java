package org.microservice1.movie.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void testMovieSettersAndGetters() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setAdult(true);
        movie.setBackdrop_path("backdrop_path");
        movie.setOriginal_language("en");
        movie.setOriginal_title("Original Title");
        movie.setOverview("Movie overview");
        movie.setPopularity(9.5);
        movie.setPoster_path("poster_path");
        movie.setRelease_date("2022-01-01");
        movie.setTitle("Movie Title");
        movie.setVideo(true);
        movie.setVote_average(8.7);
        movie.setVote_count(1000);

        assertEquals(1, movie.getId());
        assertTrue(movie.getAdult());
        assertEquals("backdrop_path", movie.getBackdrop_path());
        assertEquals("en", movie.getOriginal_language());
        assertEquals("Original Title", movie.getOriginal_title());
        assertEquals("Movie overview", movie.getOverview());
        assertEquals(9.5, movie.getPopularity());
        assertEquals("poster_path", movie.getPoster_path());
        assertEquals("2022-01-01", movie.getRelease_date());
        assertEquals("Movie Title", movie.getTitle());
        assertTrue(movie.getVideo());
        assertEquals(8.7, movie.getVote_average());
        assertEquals(1000, movie.getVote_count());
    }

    @Test
    void testMovieToString() {
        Movie movie = Movie.builder()
                .id(1)
                .adult(true)
                .backdrop_path("backdrop_path")
                .original_language("en")
                .original_title("Original Title")
                .overview("Movie overview")
                .popularity(9.5)
                .poster_path("poster_path")
                .release_date("2022-01-01")
                .title("Movie Title")
                .video(true)
                .vote_average(8.7)
                .vote_count(1000)
                .build();

        String expectedToString = "Movie(id=1, adult=true, backdrop_path=backdrop_path, " +
                "original_language=en, original_title=Original Title, overview=Movie overview, " +
                "popularity=9.5, poster_path=poster_path, release_date=2022-01-01, " +
                "title=Movie Title, video=true, vote_average=8.7, vote_count=1000)";

        assertEquals(expectedToString, movie.toString());
    }

    @Test
    void testMovieBuilder() {
        Movie movie = Movie.builder()
                .id(1)
                .adult(true)
                .backdrop_path("backdrop_path")
                .original_language("en")
                .original_title("Original Title")
                .overview("Movie overview")
                .popularity(9.5)
                .poster_path("poster_path")
                .release_date("2022-01-01")
                .title("Movie Title")
                .video(true)
                .vote_average(8.7)
                .vote_count(1000)
                .build();

        assertEquals(1, movie.getId());
        assertTrue(movie.getAdult());
        assertEquals("backdrop_path", movie.getBackdrop_path());
        assertEquals("en", movie.getOriginal_language());
        assertEquals("Original Title", movie.getOriginal_title());
        assertEquals("Movie overview", movie.getOverview());
        assertEquals(9.5, movie.getPopularity());
        assertEquals("poster_path", movie.getPoster_path());
        assertEquals("2022-01-01", movie.getRelease_date());
        assertEquals("Movie Title", movie.getTitle());
        assertTrue(movie.getVideo());
        assertEquals(8.7, movie.getVote_average());
        assertEquals(1000, movie.getVote_count());
    }
    @Test
    void testMovieEquals() {
        Movie movie1 = Movie.builder().id(1).title("Movie Title").build();
        Movie movie2 = Movie.builder().id(1).title("Movie Title").build();
        Movie movie3 = Movie.builder().id(2).title("Another Movie").build();

        // Test equals with itself
        assertEquals(movie1, movie1);

        // Test equals with equal objects
        assertEquals(movie1, movie2);
        assertEquals(movie2, movie1);

        // Test equals with different objects
        assertNotEquals(movie1, movie3);
        assertNotEquals(movie2, movie3);
    }

    @Test
    void testMovieHashCode() {
        Movie movie1 = Movie.builder().id(1).title("Movie Title").build();
        Movie movie2 = Movie.builder().id(1).title("Movie Title").build();
        Movie movie3 = Movie.builder().id(2).title("Another Movie").build();

        // Test hashCode consistency
        assertEquals(movie1.hashCode(), movie1.hashCode());
        assertEquals(movie2.hashCode(), movie2.hashCode());
        assertEquals(movie3.hashCode(), movie3.hashCode());

        // Test hashCode equality for equal objects
        assertEquals(movie1.hashCode(), movie2.hashCode());

        // Test hashCode inequality for different objects
        assertNotEquals(movie1.hashCode(), movie3.hashCode());
        assertNotEquals(movie2.hashCode(), movie3.hashCode());
    }
}

