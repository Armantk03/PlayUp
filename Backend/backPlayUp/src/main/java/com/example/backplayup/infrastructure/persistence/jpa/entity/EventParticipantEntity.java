package com.example.backplayup.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "event_participants",
        uniqueConstraints = @UniqueConstraint(columnNames = {"event_id", "user_id"})
)
@Getter
@Setter
public class EventParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
