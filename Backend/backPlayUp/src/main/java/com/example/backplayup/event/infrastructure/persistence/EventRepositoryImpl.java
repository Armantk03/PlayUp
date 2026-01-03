package com.example.backplayup.event.infrastructure.persistence;

import com.example.backplayup.event.domain.model.Event;
import com.example.backplayup.event.domain.port.out.EventRepository;
import com.example.backplayup.event.infrastructure.persistence.jpa.JpaEventRepository;
import com.example.backplayup.event.infrastructure.persistence.jpa.entity.EventEntity;
import com.example.backplayup.event.infrastructure.persistence.jpa.mapper.EventMapper;
import com.example.backplayup.eventParticipant.domain.port.out.EventParticipantRepository;
import com.example.backplayup.user.domain.port.out.UserRepository;
import com.example.backplayup.user.infrastructure.persistence.jpa.JpaUserRepository;
import com.example.backplayup.user.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class EventRepositoryImpl implements EventRepository {

    private final JpaEventRepository jpaEvent;
    private final JpaUserRepository jpaUser;
    private final EventParticipantRepository eventParticipantRepository;
    private final UserRepository userRepository;
    private final EventMapper mapper;

    public EventRepositoryImpl(
            JpaEventRepository jpaEvent,
            JpaUserRepository jpaUser,
            EventParticipantRepository eventParticipantRepository,
            UserRepository userRepository,
            EventMapper mapper
    ) {
        this.jpaEvent = jpaEvent;
        this.jpaUser = jpaUser;
        this.eventParticipantRepository = eventParticipantRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public Event save(Event event, Long creatorUserId) {

        UserEntity creator = jpaUser.findById(creatorUserId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario creador no existe"));

        EventEntity entity = mapper.toEntity(event);


        entity.setId(null);

        entity.setCreator(creator);

        return mapper.toDomain(jpaEvent.save(entity));
    }

    @Transactional
    @Override
    public Event update(Long id, Event event) {

        EventEntity entity = jpaEvent.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        if (event.getTitle() != null) entity.setTitle(event.getTitle());
        if (event.getDescription() != null) entity.setDescription(event.getDescription());
        if (event.getPhoto() != null) entity.setPhoto(event.getPhoto());
        if (event.getPoints() != 0) entity.setPoints(event.getPoints());

        return mapper.toDomain(jpaEvent.save(entity));
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return jpaEvent.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Event> findAllEvents() {
        return jpaEvent.findAll().stream().map(mapper::toDomain).toList();
    }

    @Transactional
    @Override
    public void finishEvent(Long eventId, Long userId) {

        EventEntity event = jpaEvent.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Evento no existe"));

        if (event.isFinished()) {
            throw new IllegalStateException("Evento ya finalizado");
        }

        if (!event.getCreator().getId().equals(userId)) {
            throw new IllegalArgumentException("No eres el creador del evento");
        }

        int points = event.getPoints();

        List<Long> participants =
                eventParticipantRepository.getParticipants(eventId);

        for (Long participantId : participants) {
            userRepository.addPoints(participantId, points);
        }

        event.setFinished(true);
        jpaEvent.save(event);
    }
}
