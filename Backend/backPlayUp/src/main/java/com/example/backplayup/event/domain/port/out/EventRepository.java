package com.example.backplayup.event.domain.port.out;

import com.example.backplayup.event.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    Event save(Event event, Long creatorUserId);

    Event update(Long id, Event event);

    Optional<Event> findEventById(Long id);

    List<Event> findAllEvents();
}
