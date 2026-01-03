package com.example.backplayup.mission.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    private Long id;
    private String title;
    private String description;
    private int points;
    private boolean active;
}
