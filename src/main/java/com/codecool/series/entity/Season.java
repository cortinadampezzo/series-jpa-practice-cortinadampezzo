package com.codecool.series.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Season {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String seasonCode;

    @ManyToOne
    private Series series;

    @Singular
    @OneToMany(mappedBy = "season", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private List<Episode> episodes;

}
