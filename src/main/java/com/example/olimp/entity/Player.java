package com.example.olimp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "players", uniqueConstraints = @UniqueConstraint(columnNames = "player_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    @Id
    @Column(name = "player_id", length = 10)
    private String playerId;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
}
