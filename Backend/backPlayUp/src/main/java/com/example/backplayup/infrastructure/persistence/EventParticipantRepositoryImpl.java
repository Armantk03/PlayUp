package com.example.backplayup.infrastructure.persistence;

import com.example.backplayup.eventParticipant.domain.model.EventParticipant;
import com.example.backplayup.eventParticipant.domain.port.out.EventParticipantRepository;
import com.example.backplayup.infrastructure.persistence.jpa.JpaEventParticipantRepository;
import com.example.backplayup.infrastructure.persistence.jpa.entity.EventParticipantEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventParticipantRepositoryImpl implements EventParticipantRepository {

    private final JpaEventParticipantRepository jpa;

    public EventParticipantRepositoryImpl(JpaEventParticipantRepository jpa) {
        this.jpa = jpa;
    }

    @Transactional
    @Override
    public EventParticipant joinEvent(Long eventId, Long userId) {

        jpa.findByEventIdAndUserId(eventId, userId)
                .ifPresent(p -> {
                    throw new IllegalArgumentException("Usuario ya participa en el evento");
                });

        EventParticipantEntity entity = new EventParticipantEntity();
        entity.setEventId(eventId);
        entity.setUserId(userId);

        EventParticipantEntity saved = jpa.save(entity);

        return new EventParticipant(
                saved.getId(),
                saved.getEventId(),
                saved.getUserId()
        );
    }

    @Transactional
    @Override
    public void leaveEvent(Long eventId, Long userId) {
        jpa.deleteByEventIdAndUserId(eventId, userId);
    }

    @Override
    public List<Long> getParticipants(Long eventId) {
        return jpa.findAllByEventId(eventId)
                .stream()
                .map(EventParticipantEntity::getUserId)
                .toList();
    }
}
