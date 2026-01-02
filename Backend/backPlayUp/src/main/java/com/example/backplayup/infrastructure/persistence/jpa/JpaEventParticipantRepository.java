package com.example.backplayup.infrastructure.persistence.jpa;


import com.example.backplayup.infrastructure.persistence.jpa.entity.EventParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaEventParticipantRepository
        extends JpaRepository<EventParticipantEntity, Long> {

    Optional<EventParticipantEntity> findByEventIdAndUserId(Long eventId, Long userId);

    void deleteByEventIdAndUserId(Long eventId, Long userId);

    List<EventParticipantEntity> findAllByEventId(Long eventId);
}
