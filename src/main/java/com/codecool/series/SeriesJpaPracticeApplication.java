package com.codecool.series;

import com.codecool.series.entity.Episode;
import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Season;
import com.codecool.series.entity.Series;
import com.codecool.series.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@SpringBootApplication
public class SeriesJpaPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeriesJpaPracticeApplication.class, args);
    }

    @Autowired
    private SeriesRepository seriesRepository;

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

            Episode skinsS01E01 = Episode.builder()
                    .title("Tony")
                    .episodeCode("Skins-S01E01")
                    .originalAirDate(LocalDate.of(2007,1,25))
                    .build();

            Episode skinsS01E02 = Episode.builder()
                    .title("Cassie")
                    .episodeCode("Skins-S01E02")
                    .originalAirDate(LocalDate.of(2007,2,1))
                    .build();

            Episode skinsS02E09 = Episode.builder()
                    .title("Cassie")
                    .episodeCode("Skins-S02E09")
                    .originalAirDate(LocalDate.of(2008,4, 7))
                    .build();

            Episode skinsS02E10 = Episode.builder()
                    .title("Final Goodbyes")
                    .episodeCode("Skins-S02E10")
                    .originalAirDate(LocalDate.of(2008,4,14))
                    .build();

            Season skinsS01 = Season.builder()
                    .seasonCode("Skins-S01")
                    .episode(skinsS01E01)
                    .episode(skinsS01E02)
                    .build();

            Season skinsS02 = Season.builder()
                    .seasonCode("Skins-S02")
                    .episode(skinsS02E09)
                    .episode(skinsS02E10)
                    .build();

            skinsS01E01.setSeason(skinsS01);
            skinsS01E02.setSeason(skinsS01);
            skinsS02E09.setSeason(skinsS02);
            skinsS02E10.setSeason(skinsS02);

            Series skins = Series.builder()
                    .title("Skins")
                    .genre(Genre.DRAMA)
                    .season(skinsS01)
                    .season(skinsS02)
                    .build();

            skinsS01.setSeries(skins);
            skinsS02.setSeries(skins);

            seriesRepository.save(skins);
        };
    }

}
