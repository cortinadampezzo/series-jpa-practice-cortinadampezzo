package com.codecool.series.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Series {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Singular
    @OneToMany(mappedBy = "series", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    private List<Season> seasons;

}
