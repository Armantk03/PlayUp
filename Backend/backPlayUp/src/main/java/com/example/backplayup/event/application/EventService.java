package com.example.backplayup.event.application;

import com.example.backplayup.event.domain.model.Event;
import com.example.backplayup.event.domain.port.in.EventUseCase;
import com.example.backplayup.event.domain.port.out.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService implements EventUseCase {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event, Long creatorUserId) {
        return repository.save(event, creatorUserId);
    }

    @Override
    public Event update(Long id, Event event) {
        return repository.update(id, event);
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return repository.findEventById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return repository.findAllEvents();
    }

    @Override
    public void finishEvent(Long eventId, Long userId) {
        repository.finishEvent(eventId, userId);
    }
}
