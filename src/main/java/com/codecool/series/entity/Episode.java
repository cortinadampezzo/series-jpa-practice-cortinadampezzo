package com.codecool.series.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Episode {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String episodeCode;

    @ManyToOne
    private Season season;

    private LocalDate originalAirDate;

}
