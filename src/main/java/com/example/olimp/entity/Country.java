package com.example.olimp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "countries", uniqueConstraints = @UniqueConstraint(columnNames = "country_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @Column(name = "country_id", length = 3)
    private String countryId;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "area_sqkm", nullable = false)
    private Integer areaSqkm;

    @Column(name = "population", nullable = false)
    private Integer population;
}