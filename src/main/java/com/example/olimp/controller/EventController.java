package com.example.olimp.controller;

import com.example.olimp.entity.Event;
import com.example.olimp.repository.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events/ties")
    public List<Event> getEventsWithTies() {
        return eventRepository.findIndividualEventsWithTieAndMultipleGolds();
    }
}
