package com.example.backplayup.mission.domain.port.in;

import com.example.backplayup.mission.domain.model.Mission;
import com.example.backplayup.mission.domain.model.UserMission;

import java.util.List;

public interface MissionUseCase {

    Mission create(Mission mission);
    List<Mission> getAll();
    UserMission assignMission(Long userId, Long missionId);
    UserMission completeMission(Long userId, Long missionId);
}
