package com.example.backplayup.user.infrastructure.persistence.jpa.entity;

import com.example.backplayup.event.infrastructure.persistence.jpa.entity.EventEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int puntos;

    @Column(nullable = false)
    private int nivel;

    @Column(nullable = false)
    private LocalDateTime registro;

    @Column(nullable = false)
    private String language;

    @OneToMany(
            mappedBy = "creator",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EventEntity> events = new ArrayList<>();
}

