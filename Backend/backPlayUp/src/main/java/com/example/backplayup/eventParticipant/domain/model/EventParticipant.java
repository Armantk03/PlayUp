package com.example.backplayup.eventParticipant.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventParticipant {

    private Long id;
    private Long eventId;
    private Long userId;
}
