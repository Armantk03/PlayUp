package com.example.backplayup.event.infrastructure.controller;

import com.example.backplayup.event.domain.model.Event;
import com.example.backplayup.event.domain.port.in.EventUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventUseCase eventUseCase;

    public EventController(EventUseCase eventUseCase) {
        this.eventUseCase = eventUseCase;
    }

    @PostMapping
    public Event createEvent(
            @RequestParam Long creatorUserId,
            @RequestBody Event event
    ) {
        return eventUseCase.create(event, creatorUserId);
    }

    @PatchMapping("/{id}")
    public Event updateEvent(
            @PathVariable Long id,
            @RequestBody Event event
    ) {
        return eventUseCase.update(id, event);
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventUseCase.getEventById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventUseCase.getAllEvents();
    }
}
