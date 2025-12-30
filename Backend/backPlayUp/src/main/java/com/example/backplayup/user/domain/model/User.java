package com.example.backplayup.user.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter


public class User {

    private Long id;
    private String nombre;
    private String email;
    private int puntos;
    private int nivel;
    private LocalDateTime registro;
    private String language;


}



