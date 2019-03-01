package com.codecool.series;

import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Series;
import com.codecool.series.repository.SeriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeriesJpaPracticeApplicationTests {

    @Autowired
    private SeriesRepository seriesRepository;

    @Test
    public void saveOneSimple() {
        Series boJackHorseman = Series.builder()
                .title("BoJack Horseman")
                .genre(Genre.ANIMATION)
                .build();

        seriesRepository.save(boJackHorseman);

        List<Series> series = seriesRepository.findAll();
        assertThat(series).hasSize(1);
    }

}
