package com.example.backplayup.auth.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {

    private Long userId;
    private String passwordHash;
    private Role role;
}
