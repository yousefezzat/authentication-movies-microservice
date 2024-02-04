package org.microservice1.movie.config;

import lombok.RequiredArgsConstructor;
import org.microservice1.movie.repository.MovieRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.microservice1.movie.entity.Movie;

import java.util.Arrays;
import java.util.List;

//@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MovieRepo movieRepo;



    @Override
    public void run(String... args) {
        List<Movie> movies = Arrays.asList(
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
                        .build(),
                Movie.builder()
                        .id(240)
                        .adult(false)
                        .backdrop_path("/kGzFbGhp99zva6oZODW5atUtnqi.jpg")
                        .original_language("en")
                        .original_title("The Godfather Part II")
                        .overview("In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.")
                        .popularity(61.145)
                        .poster_path("/hek3koDUyRQk7FIhPXsa6mT2Zc3.jpg")
                        .release_date("1974-12-20")
                        .title("The Godfather Part II")
                        .video(false)
                        .vote_average(8.592)
                        .vote_count(11688)
                        .build(),
                Movie.builder()
                        .id(424)
                        .adult(false)
                        .backdrop_path("/3f92DMBTFqr3wgXpfxzrb0qv8nG.jpg")
                        .original_language("en")
                        .original_title("Schindler's List")
                        .overview("The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.")
                        .popularity(69.046)
                        .poster_path("/sF1U4EUQS8YHUYjNl3pMGNIQyr0.jpg")
                        .release_date("1993-12-15")
                        .title("Schindler's List")
                        .video(false)
                        .vote_average(8.572)
                        .vote_count(15059)
                        .build(),
                Movie.builder()
                        .id(389)
                        .adult(false)
                        .backdrop_path("/qqHQsStV6exghCM7zbObuYBiYxw.jpg")
                        .original_language("en")
                        .original_title("12 Angry Men")
                        .overview("The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.")
                        .popularity(40.162)
                        .poster_path("/ow3wq89wM8qd5X7hWKxiRfsFf9C.jpg")
                        .release_date("1957-04-10")
                        .title("12 Angry Men")
                        .video(false)
                        .vote_average(8.547)
                        .vote_count(7987)
                        .build(),
                Movie.builder()
                        .id(19404)
                        .adult(false)
                        .backdrop_path("/90ez6ArvpO8bvpyIngBuwXOqJm5.jpg")
                        .original_language("hi")
                        .original_title("दिलवाले दुल्हनिया ले जायेंगे")
                        .overview("Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.")
                        .popularity(29.351)
                        .poster_path("/lfRkUr7DYdHldAqi3PwdQGBRBPM.jpg")
                        .release_date("1995-10-20")
                        .title("Dilwale Dulhania Le Jayenge")
                        .video(false)
                        .vote_average(8.541)
                        .vote_count(4327)
                        .build(),
                Movie.builder()
                        .id(129)
                        .adult(false)
                        .backdrop_path("/mSDsSDwaP3E7dEfUPWy4J0djt4O.jpg")
                        .original_language("ja")
                        .original_title("千と千尋の神隠し")
                        .overview("A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she must call upon the courage she never knew she had to free her family.")
                        .popularity(98.603)
                        .poster_path("/39wmItIWsg5sZMyRUHLkWBcuVCM.jpg")
                        .release_date("2001-07-20")
                        .title("Spirited Away")
                        .video(false)
                        .vote_average(8.539)
                        .vote_count(15446)
                        .build(),
                Movie.builder()
                        .id(155)
                        .adult(false)
                        .backdrop_path("/nMKdUUepR0i5zn0y1T4CsSB5chy.jpg")
                        .original_language("en")
                        .original_title("The Dark Knight")
                        .overview("Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.")
                        .popularity(100.557)
                        .poster_path("/qJ2tW6WMUDux911r6m7haRef0WH.jpg")
                        .release_date("2008-07-16")
                        .title("The Dark Knight")
                        .video(false)
                        .vote_average(8.515)
                        .vote_count(31362)
                        .build(),
                Movie.builder()
                        .id(496243)
                        .adult(false)
                        .backdrop_path("/hiKmpZMGZsrkA3cdce8a7Dpos1j.jpg")
                        .original_language("ko")
                        .original_title("기생충")
                        .overview("All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.")
                        .popularity(89.246)
                        .poster_path("/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg")
                        .release_date("2019-05-30")
                        .title("Parasite")
                        .video(false)
                        .vote_average(8.513)
                        .vote_count(17026)
                        .build(),
                Movie.builder()
                        .id(497)
                        .adult(false)
                        .backdrop_path("/l6hQWH9eDksNJNiXWYRkWqikOdu.jpg")
                        .original_language("en")
                        .original_title("The Green Mile")
                        .overview("A supernatural tale set on death row in a Southern prison, where gentle giant John Coffey possesses the mysterious power to heal people's ailments. When the cell block's head guard, Paul Edgecomb, recognizes Coffey's miraculous gift, he tries desperately to help stave off the condemned man's execution.")
                        .popularity(61.041)
                        .poster_path("/8VG8fDNiy50H4FedGwdSVUPoaJe.jpg")
                        .release_date("1999-12-10")
                        .title("The Green Mile")
                        .video(false)
                        .vote_average(8.511)
                        .vote_count(16411)
                        .build(),
                Movie.builder()
                        .id(372058)
                        .adult(false)
                        .backdrop_path("/dIWwZW7dJJtqC6CgWzYkNVKIUm8.jpg")
                        .original_language("ja")
                        .original_title("君の名は。")
                        .overview("High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places. Mitsuha wakes up in Taki’s body, and he in hers. This bizarre occurrence continues to happen randomly, and the two must adjust their lives around each other.")
                        .popularity(76.137)
                        .poster_path("/q719jXXEzOoYaps6babgKnONONX.jpg")
                        .release_date("2016-08-26")
                        .title("Your Name.")
                        .video(false)
                        .vote_average(8.498)
                        .vote_count(10715)
                        .build(),
                Movie.builder()
                        .id(680)
                        .adult(false)
                        .backdrop_path("/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg")
                        .original_language("en")
                        .original_title("Pulp Fiction")
                        .overview("A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.")
                        .popularity(105.211)
                        .poster_path("/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg")
                        .release_date("1994-09-10")
                        .title("Pulp Fiction")
                        .video(false)
                        .vote_average(8.489)
                        .vote_count(26558)
                        .build(),
                Movie.builder()
                        .id(429)
                        .adult(false)
                        .backdrop_path("/x4biAVdPVCghBlsVIzB6NmbghIz.jpg")
                        .original_language("it")
                        .original_title("Il buono, il brutto, il cattivo")
                        .overview("While the Civil War rages on between the Union and the Confederacy, three men – a quiet loner, a ruthless hitman, and a Mexican bandit – comb the American Southwest in search of a strongbox containing $200,000 in stolen gold.")
                        .popularity(55.442)
                        .poster_path("/bX2xnavhMYjWDoZp1VM6VnU1xwe.jpg")
                        .release_date("1966-12-22")
                        .title("The Good, the Bad and the Ugly")
                        .video(false)
                        .vote_average(8.47)
                        .vote_count(8039)
                        .build(),
                Movie.builder()
                        .id(769)
                        .adult(false)
                        .backdrop_path("/sw7mordbZxgITU877yTpZCud90M.jpg")
                        .original_language("en")
                        .original_title("GoodFellas")
                        .overview("The true story of Henry Hill, a half-Irish, half-Sicilian Brooklyn kid who is adopted by neighbourhood gangsters at an early age and climbs the ranks of a Mafia family under the guidance of Jimmy Conway.")
                        .popularity(59.504)
                        .poster_path("/aKuFiU82s5ISJpGZp7YkIr3kCUd.jpg")
                        .release_date("1990-09-12")
                        .title("GoodFellas")
                        .video(false)
                        .vote_average(8.468)
                        .vote_count(12121)
                        .build(),
                Movie.builder()
                        .id(346)
                        .adult(false)
                        .backdrop_path("/qvZ91FwMq6O47VViAr8vZNQz3WI.jpg")
                        .original_language("ja")
                        .original_title("七人の侍")
                        .overview("A samurai answers a village's request for protection after he falls on hard times. The town needs protection from bandits, so the samurai gathers six others to help him teach the people how to defend themselves, and the villagers provide the soldiers with food.")
                        .popularity(35.522)
                        .poster_path("/8OKmBV5BUFzmozIC3pPWKHy17kx.jpg")
                        .release_date("1954-04-26")
                        .title("Seven Samurai")
                        .video(false)
                        .vote_average(8.458)
                        .vote_count(3383)
                        .build(),
                Movie.builder()
                        .id(12477)
                        .adult(false)
                        .backdrop_path("/dlC0ed9Ugh3FzydnkBtV5lRXUu4.jpg")
                        .original_language("ja")
                        .original_title("火垂るの墓")
                        .overview("In the final months of World War II, 14-year-old Seita and his sister Setsuko are orphaned when their mother is killed during an air raid in Kobe, Japan. After a falling out with their aunt, they move into an abandoned bomb shelter. With no surviving relatives and their emergency rations depleted, Seita and Setsuko struggle to survive.")
                        .popularity(0.6)
                        .poster_path("/k9tv1rXZbOhH7eiCk378x61kNQ1.jpg")
                        .release_date("1988-04-16")
                        .title("Grave of the Fireflies")
                        .video(false)
                        .vote_average(8.5)
                        .vote_count(5071)
                        .build(),
                Movie.builder()
                        .id(637)
                        .adult(false)
                        .backdrop_path("/gavyCu1UaTaTNPsVaGXT6pe5u24.jpg")
                        .original_language("it")
                        .original_title("La vita è bella")
                        .overview("A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.")
                        .popularity(41.241)
                        .poster_path("/6tEJnof1DKWPnl5lzkjf0FVv7oB.jpg")
                        .release_date("1997-12-20")
                        .title("Life Is Beautiful")
                        .video(false)
                        .vote_average(8.456)
                        .vote_count(12530)
                        .build(),
                Movie.builder()
                        .id(11216)
                        .adult(false)
                        .backdrop_path("/zoVeIgKzGJzpdG6Gwnr7iOYfIMU.jpg")
                        .original_language("it")
                        .original_title("Nuovo Cinema Paradiso")
                        .overview("A filmmaker recalls his childhood, when he fell in love with the movies at his village's theater and formed a deep friendship with the theater's projectionist.")
                        .popularity(32.674)
                        .poster_path("/8SRUfRUi6x4O68n0VCbDNRa6iGL.jpg")
                        .release_date("1988-11-17")
                        .title("Cinema Paradiso")
                        .video(false)
                        .vote_average(8.45)
                        .vote_count(4100)
                        .build()
        );

        movieRepo.saveAll(movies);
    }
}