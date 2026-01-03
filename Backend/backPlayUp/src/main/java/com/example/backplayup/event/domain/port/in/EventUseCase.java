package com.example.backplayup.event.domain.port.in;

import com.example.backplayup.event.domain.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventUseCase {

    Event create(Event event, Long creatorUserId);

    Event update(Long id, Event event);

    Optional<Event> getEventById(Long id);

    List<Event> getAllEvents();

    void finishEvent(Long eventId, Long userId);
}
