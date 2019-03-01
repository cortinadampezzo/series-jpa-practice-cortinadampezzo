package com.codecool.series.entity;

import lombok.*;
import javax.persistence.*;

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

}
