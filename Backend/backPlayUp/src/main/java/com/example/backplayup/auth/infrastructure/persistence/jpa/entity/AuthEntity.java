package com.example.backplayup.auth.infrastructure.persistence.jpa.entity;

import com.example.backplayup.auth.domain.model.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "auth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
