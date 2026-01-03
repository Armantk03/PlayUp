package com.example.backplayup.mission.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMission {

    private Long id;
    private Long userId;
    private Long missionId;
    private boolean completed;
}
