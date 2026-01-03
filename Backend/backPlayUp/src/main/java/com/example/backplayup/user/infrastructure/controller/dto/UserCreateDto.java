package com.example.backplayup.user.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String nombre;
    private String email;
    private String language;
    private String password;

}
