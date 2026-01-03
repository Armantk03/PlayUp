package com.example.backplayup.mission.domain.port.out;

import com.example.backplayup.mission.domain.model.Mission;
import com.example.backplayup.mission.domain.model.UserMission;

import java.util.List;

public interface MissionRepository {

    Mission create(Mission mission);
    List<Mission> getAll();
    UserMission assignMission(Long userId, Long missionId);
    UserMission completeMission(Long userId, Long missionId);
}
