package com.example.backplayup.eventParticipant.infrastructure.controller;

import com.example.backplayup.eventParticipant.domain.model.EventParticipant;
import com.example.backplayup.eventParticipant.domain.port.in.EventParticipantUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/{eventId}/participants")
public class EventParticipantController {

    private final EventParticipantUseCase useCase;

    public EventParticipantController(EventParticipantUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/{userId}")
    public EventParticipant join(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        return useCase.joinEvent(eventId, userId);
    }

    @DeleteMapping("/{userId}")
    public void leave(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        useCase.leaveEvent(eventId, userId);
    }

    @GetMapping
    public List<Long> participants(@PathVariable Long eventId) {
        return useCase.getParticipants(eventId);
    }
}
