package com.example.backplayup.eventParticipant.infrastructure.controller;

import com.example.backplayup.eventParticipant.domain.model.EventParticipant;
import com.example.backplayup.eventParticipant.domain.port.in.EventParticipantUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Event Participants",
        description = "Gestión de participación de usuarios en eventos"
)
@RestController
@RequestMapping("/events/{eventId}/participants")
public class EventParticipantController {

    private final EventParticipantUseCase useCase;

    public EventParticipantController(EventParticipantUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Unirse a un evento",
            description = "Añade un usuario como participante de un evento"
    )
    @PostMapping("/{userId}")
    public EventParticipant join(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        return useCase.joinEvent(eventId, userId);
    }

    @Operation(
            summary = "Salir de un evento",
            description = "Elimina a un usuario de la lista de participantes de un evento"
    )
    @DeleteMapping("/{userId}")
    public void leave(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        useCase.leaveEvent(eventId, userId);
    }

    @Operation(
            summary = "Obtener participantes de un evento",
            description = "Devuelve la lista de identificadores de usuarios participantes en un evento"
    )
    @GetMapping
    public List<Long> participants(@PathVariable Long eventId) {
        return useCase.getParticipants(eventId);
    }
}
