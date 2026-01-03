package com.example.backplayup.user.infrastructure.controller;

import com.example.backplayup.user.domain.model.User;
import com.example.backplayup.user.domain.port.in.UserUseCase;
import com.example.backplayup.user.infrastructure.controller.dto.LanguageDTO;
import com.example.backplayup.user.infrastructure.controller.dto.PointsDTO;
import com.example.backplayup.user.infrastructure.controller.dto.UserCreateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Users",
        description = "Operaciones relacionadas con usuarios: registro, gestión, ranking y progreso"
)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Operation(
            summary = "Crear usuario",
            description = "Crea un nuevo usuario y su autenticación asociada"
    )
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto user) {

        User created = userUseCase.create(
                user.getNombre(),
                user.getEmail(),
                user.getLanguage(),
                user.getPassword()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }

    @Operation(
            summary = "Obtener usuario por ID",
            description = "Devuelve la información de un usuario a partir de su identificador"
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        return userUseCase.getByUserId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Cambiar idioma del usuario",
            description = "Actualiza el idioma preferido de un usuario"
    )
    @PatchMapping("/{id}/language")
    public ResponseEntity<User> changeLanguage(
            @PathVariable Long id,
            @RequestBody LanguageDTO languageDTO) {

        User updated = userUseCase.changeUserLanguage(id, languageDTO.getLanguage());
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Añadir puntos al usuario",
            description = "Suma puntos al usuario (usado al finalizar eventos o completar misiones)"
    )
    @PatchMapping("/{id}/points")
    public ResponseEntity<User> addPoints(
            @PathVariable Long id,
            @RequestBody PointsDTO pointsDTO) {

        User updated = userUseCase.addPoints(id, pointsDTO.getPoints());
        return ResponseEntity.ok(updated);
    }

    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario y toda su información asociada"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Obtener ranking global",
            description = "Devuelve el ranking completo de usuarios ordenado por puntos"
    )
    @GetMapping("/ranking")
    public ResponseEntity<List<User>> getRanking() {
        return ResponseEntity.ok(userUseCase.getRanking());
    }

    @Operation(
            summary = "Obtener top del ranking",
            description = "Devuelve los primeros usuarios del ranking según el límite indicado"
    )
    @GetMapping("/ranking/top/{limit}")
    public ResponseEntity<List<User>> getTopRanking(@PathVariable int limit) {
        return ResponseEntity.ok(userUseCase.getTopRanking(limit));
    }
}
