package com.example.backplayup.eventParticipant.domain.port.out;



import com.example.backplayup.eventParticipant.domain.model.EventParticipant;

import java.util.List;


public interface EventParticipantRepository {

    EventParticipant joinEvent(Long eventId, Long userId);

    void leaveEvent(Long eventId, Long userId);

    List<Long> getParticipants(Long eventId);
}