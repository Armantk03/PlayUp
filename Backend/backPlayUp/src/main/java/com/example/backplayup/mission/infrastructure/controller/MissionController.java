package com.example.backplayup.mission.infrastructure.controller;

import com.example.backplayup.mission.domain.model.Mission;
import com.example.backplayup.mission.domain.model.UserMission;
import com.example.backplayup.mission.domain.port.in.MissionUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Missions",
        description = "Gestión de misiones y progreso de usuarios"
)
@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionUseCase useCase;

    public MissionController(MissionUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(
            summary = "Crear misión",
            description = "Crea una nueva misión disponible para los usuarios"
    )
    @PostMapping
    public Mission create(@RequestBody Mission mission) {
        return useCase.create(mission);
    }

    @Operation(
            summary = "Obtener todas las misiones",
            description = "Devuelve la lista completa de misiones disponibles"
    )
    @GetMapping
    public List<Mission> getAll() {
        return useCase.getAll();
    }

    @Operation(
            summary = "Asignar misión a usuario",
            description = "Asigna una misión concreta a un usuario"
    )
    @PostMapping("/{missionId}/assign/{userId}")
    public UserMission assign(
            @PathVariable Long missionId,
            @PathVariable Long userId
    ) {
        return useCase.assignMission(userId, missionId);
    }

    @Operation(
            summary = "Completar misión",
            description = "Marca una misión como completada y suma los puntos al usuario"
    )
    @PostMapping("/{missionId}/complete/{userId}")
    public UserMission complete(
            @PathVariable Long missionId,
            @PathVariable Long userId
    ) {
        return useCase.completeMission(userId, missionId);
    }
}
