package com.codecool.series;

import com.codecool.series.entity.Episode;
import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Season;
import com.codecool.series.entity.Series;
import com.codecool.series.repository.EpisodeRepository;
import com.codecool.series.repository.SeasonRepository;
import com.codecool.series.repository.SeriesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeriesJpaPracticeApplicationTests {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

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

    @Test(expected = DataIntegrityViolationException.class)
    public void titleShouldBeNotNull() {
        Series boJackHorseman = Series.builder()
                .genre(Genre.ANIMATION)
                .build();

        seriesRepository.save(boJackHorseman);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqueSeasonCodeTwice() {
        Season skinsS01 = Season.builder()
                .seasonCode("Skins-01")
                .build();

        seasonRepository.save(skinsS01);

        Season skinsS02 = Season.builder()
                .seasonCode("Skins-01")
                .build();

        seasonRepository.saveAndFlush(skinsS02);
    }

    @Test
    public void persistence() {
        Episode blackMirrorS03E04 = Episode.builder()
                .title("San Junipero")
                .episodeCode("Black Mirror-S03E04")
                .originalAirDate(LocalDate.of(2016, 10, 21))
                .build();

        Season blackMirrorS03 = Season.builder()
                .seasonCode("Black Mirror-S03")
                .episode(blackMirrorS03E04)
                .build();

        Series blackMirror = Series.builder()
                .title("Black Mirror")
                .genre(Genre.DRAMA)
                .season(blackMirrorS03)
                .build();

        seriesRepository.save(blackMirror);

        List<Episode> episodes = episodeRepository.findAll();
        assertThat(episodes)
                .hasSize(1)
                .allMatch(episode1 -> episode1.getId() > 0L);

        List<Season> seasons = seasonRepository.findAll();
        assertThat(seasons)
                .hasSize(1)
                .allMatch(season1 -> season1.getId() > 0L);
    }

}
