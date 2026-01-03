package com.example.backplayup.mission.application;

import com.example.backplayup.mission.domain.model.Mission;
import com.example.backplayup.mission.domain.model.UserMission;
import com.example.backplayup.mission.domain.port.in.MissionUseCase;
import com.example.backplayup.mission.domain.port.out.MissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService implements MissionUseCase {

    private final MissionRepository repository;

    public MissionService(MissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mission create(Mission mission) {
        return repository.create(mission);
    }

    @Override
    public List<Mission> getAll() {
        return repository.getAll();
    }

    @Override
    public UserMission assignMission(Long userId, Long missionId) {
        return repository.assignMission(userId, missionId);
    }

    @Override
    public UserMission completeMission(Long userId, Long missionId) {
        return repository.completeMission(userId, missionId);
    }
}
