package com.example.backplayup.eventParticipant.domain.port.in;



import com.example.backplayup.eventParticipant.domain.model.EventParticipant;

import java.util.List;

public interface EventParticipantUseCase {

    EventParticipant joinEvent(Long eventId, Long userId);

    void leaveEvent(Long eventId, Long userId);

    List<Long> getParticipants(Long eventId);
}
