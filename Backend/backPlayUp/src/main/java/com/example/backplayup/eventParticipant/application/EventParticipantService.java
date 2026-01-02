package com.example.backplayup.eventParticipant.application;

import com.example.backplayup.eventParticipant.domain.model.EventParticipant;
import com.example.backplayup.eventParticipant.domain.port.in.EventParticipantUseCase;
import com.example.backplayup.eventParticipant.domain.port.out.EventParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventParticipantService implements EventParticipantUseCase {

    private final EventParticipantRepository repository;

    public EventParticipantService(EventParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventParticipant joinEvent(Long eventId, Long userId) {
        return repository.joinEvent(eventId, userId);
    }

    @Override
    public void leaveEvent(Long eventId, Long userId) {
        repository.leaveEvent(eventId, userId);
    }

    @Override
    public List<Long> getParticipants(Long eventId) {
        return repository.getParticipants(eventId);
    }
}
