package com.example.olimp.repository;

import com.example.olimp.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    @Query("SELECT e FROM Event e " +
            "WHERE e.isTeamEvent = 0 AND " +
            "(SELECT COUNT(r.medal) FROM Result r WHERE r.event = e AND r.medal = 'GOLD') >= 2")
    List<Event> findIndividualEventsWithTieAndMultipleGolds();
}

