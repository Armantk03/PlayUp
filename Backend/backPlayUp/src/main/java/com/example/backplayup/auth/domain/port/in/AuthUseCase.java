package com.example.backplayup.auth.domain.port.in;

import com.example.backplayup.auth.domain.model.Auth;
import com.example.backplayup.auth.domain.model.AuthResult;


public interface AuthUseCase {

    Auth register(Long userId,String password);
    AuthResult login(String email, String password);

}
