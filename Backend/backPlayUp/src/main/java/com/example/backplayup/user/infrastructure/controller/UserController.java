package com.example.backplayup.user.infrastructure.controller;

import com.example.backplayup.user.domain.model.User;
import com.example.backplayup.user.domain.port.in.UserUseCase;
import com.example.backplayup.user.infrastructure.controller.dto.LanguageDTO;
import com.example.backplayup.user.infrastructure.controller.dto.PointsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User created = userUseCase.create(
                user.getNombre(),
                user.getEmail(),
                user.getLanguage()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(created);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {

        return userUseCase.getByUserId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PatchMapping("/{id}/language")
    public ResponseEntity<User> changeLanguage(
            @PathVariable Long id,
            @RequestBody LanguageDTO languageDTO) {

        User updated = userUseCase.changeUserLanguage(id, languageDTO.getLanguage());
        return ResponseEntity.ok(updated);
    }


    @PatchMapping("/{id}/points")
    public ResponseEntity<User> addPoints(
            @PathVariable Long id,
            @RequestBody PointsDTO pointsDTO) {

        User updated = userUseCase.addPoints(id, pointsDTO.getPoints());
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
