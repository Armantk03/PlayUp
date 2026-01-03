package com.example.backplayup.auth.infrastructure.controller;

import com.example.backplayup.auth.application.AuthService;
import com.example.backplayup.auth.domain.model.AuthResult;
import com.example.backplayup.auth.infrastructure.controller.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication",
        description = "Autenticación de usuarios (login)"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @Operation(
            summary = "Iniciar sesión",
            description = "Valida las credenciales del usuario y devuelve su identificador y rol"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResult> login(@RequestBody LoginRequest request) {

        AuthResult result = service.login(
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.ok(result);
    }
}
