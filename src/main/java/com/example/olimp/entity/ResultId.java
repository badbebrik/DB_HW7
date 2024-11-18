package com.example.olimp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultId implements Serializable {
    @Column(name = "event_id", length = 7)
    private String eventId;

    @Column(name = "player_id", length = 10)
    private String playerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultId resultId = (ResultId) o;

        if (!Objects.equals(eventId, resultId.eventId)) return false;
        return Objects.equals(playerId, resultId.playerId);
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
        return result;
    }
}