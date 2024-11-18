package com.example.olimp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "olympics", uniqueConstraints = @UniqueConstraint(columnNames = "olympic_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Olympics {
    @Id
    @Column(name = "olympic_id", length = 7)
    private String olympicId;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "startdate", nullable = false)
    private LocalDate startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDate endDate;
}
