package com.codecool.series;

import com.codecool.series.entity.Genre;
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
            Series skins = Series.builder()
                    .title("Skins")
                    .genre(Genre.DRAMA)
                    .build();

            seriesRepository.save(skins);
        };
    }

}
