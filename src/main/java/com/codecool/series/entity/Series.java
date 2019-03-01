package com.codecool.series.entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Series {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;

}
