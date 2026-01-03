package com.example.backplayup.event.infrastructure.controller;

import com.example.backplayup.event.domain.model.Event;
import com.example.backplayup.event.domain.port.in.EventUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Events",
        description = "Gestión de eventos: creación, actualización, consulta y finalización"
)
@RestController
@RequestMapping("/events")
public class EventController {

    private final EventUseCase eventUseCase;

    public EventController(EventUseCase eventUseCase) {
        this.eventUseCase = eventUseCase;
    }

    @Operation(
            summary = "Crear evento",
            description = "Crea un evento asociado a un usuario creador"
    )
    @PostMapping
    public Event createEvent(
            @RequestParam Long creatorUserId,
            @RequestBody Event event
    ) {
        return eventUseCase.create(event, creatorUserId);
    }

    @Operation(
            summary = "Actualizar evento",
            description = "Actualiza los datos de un evento existente"
    )
    @PatchMapping("/{id}")
    public Event updateEvent(
            @PathVariable Long id,
            @RequestBody Event event
    ) {
        return eventUseCase.update(id, event);
    }

    @Operation(
            summary = "Obtener evento por ID",
            description = "Devuelve la información de un evento concreto"
    )
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventUseCase.getEventById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
    }

    @Operation(
            summary = "Obtener todos los eventos",
            description = "Devuelve la lista completa de eventos disponibles"
    )
    @GetMapping
    public List<Event> getAllEvents() {
        return eventUseCase.getAllEvents();
    }

    @Operation(
            summary = "Finalizar evento",
            description = "Finaliza un evento y reparte los puntos entre los participantes"
    )
    @PostMapping("/{eventId}/finish")
    public void finishEvent(
            @PathVariable Long eventId,
            @RequestParam Long userId
    ) {
        eventUseCase.finishEvent(eventId, userId);
    }
}
