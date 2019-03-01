package com.codecool.series;

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

            Season skinsS01 = Season.builder()
                    .seasonCode("S01")
                    .build();

            Season skinsS02 = Season.builder()
                    .seasonCode("S02")
                    .build();

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
