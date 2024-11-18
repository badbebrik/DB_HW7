package com.example.olimp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "events",
        uniqueConstraints = @UniqueConstraint(columnNames = "event_id"),
        indexes = @Index(columnList = "olympic_id")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @Column(name = "event_id", length = 7)
    private String eventId;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "eventtype", length = 20, nullable = false)
    private String eventType;

    @ManyToOne
    @JoinColumn(name = "olympic_id", nullable = false)
    private Olympics olympics;

    @Column(name = "is_team_event", nullable = false)
    private Integer isTeamEvent;

    @Column(name = "num_players_in_team")
    private Integer numPlayersInTeam;

    @Column(name = "result_noted_in", length = 100)
    private String resultNotedIn;
}