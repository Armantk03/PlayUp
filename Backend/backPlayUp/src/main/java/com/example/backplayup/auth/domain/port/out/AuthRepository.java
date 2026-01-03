package com.example.backplayup.auth.domain.port.out;

import com.example.backplayup.auth.domain.model.Auth;
import com.example.backplayup.auth.domain.model.AuthResult;

public interface AuthRepository {

    Auth register(Long userId, String password);
    AuthResult login(String email, String password);
}
