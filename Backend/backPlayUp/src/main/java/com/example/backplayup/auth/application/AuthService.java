package com.example.backplayup.auth.application;

import com.example.backplayup.auth.domain.model.Auth;
import com.example.backplayup.auth.domain.model.AuthResult;
import com.example.backplayup.auth.domain.port.in.AuthUseCase;
import com.example.backplayup.auth.domain.port.out.AuthRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {

    AuthRepository repo;

    public AuthService(AuthRepository repo) {
        this.repo = repo;
    }


    @Override
    public Auth register(Long userId, String password) {
        return repo.register(userId,password);
    }

    @Override
    public AuthResult login(String email, String password) {
        return repo.login(email, password);
    }
}
