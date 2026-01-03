package com.example.backplayup.mission.infrastructure.persistence;

import com.example.backplayup.mission.domain.model.Mission;
import com.example.backplayup.mission.domain.model.UserMission;
import com.example.backplayup.mission.domain.port.out.MissionRepository;
import com.example.backplayup.mission.infrastructure.persistence.jpa.JpaMissionRepository;
import com.example.backplayup.mission.infrastructure.persistence.jpa.JpaUserMissionRepository;
import com.example.backplayup.mission.infrastructure.persistence.jpa.entity.MissionEntity;
import com.example.backplayup.mission.infrastructure.persistence.jpa.entity.UserMissionEntity;
import com.example.backplayup.mission.infrastructure.persistence.jpa.mapper.MissionMapper;
import com.example.backplayup.mission.infrastructure.persistence.jpa.mapper.UserMissionMapper;
import com.example.backplayup.user.infrastructure.persistence.jpa.JpaUserRepository;
import com.example.backplayup.user.infrastructure.persistence.jpa.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MissionRepositoryImpl implements MissionRepository {

    private final JpaMissionRepository missionJpa;
    private final JpaUserMissionRepository userMissionJpa;
    private final JpaUserRepository userJpa;
    private final MissionMapper missionMapper;
    private final UserMissionMapper userMissionMapper;

    public MissionRepositoryImpl(
            JpaMissionRepository missionJpa,
            JpaUserMissionRepository userMissionJpa,
            JpaUserRepository userJpa,
            MissionMapper missionMapper,
            UserMissionMapper userMissionMapper
    ) {
        this.missionJpa = missionJpa;
        this.userMissionJpa = userMissionJpa;
        this.userJpa = userJpa;
        this.missionMapper = missionMapper;
        this.userMissionMapper = userMissionMapper;
    }

    @Transactional
    @Override
    public Mission create(Mission mission) {
        MissionEntity entity = missionMapper.toEntity(mission);
        entity.setId(null);
        entity.setActive(true);
        return missionMapper.toDomain(missionJpa.save(entity));
    }

    @Transactional
    @Override
    public List<Mission> getAll() {
        return missionJpa.findAll()
                .stream()
                .map(missionMapper::toDomain)
                .toList();
    }

    @Transactional
    @Override
    public UserMission assignMission(Long userId, Long missionId) {

        userJpa.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User no existe"));

        missionJpa.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission no existe"));

        if (userMissionJpa.findByUserIdAndMissionId(userId, missionId).isPresent()) {
            throw new IllegalArgumentException("Mission ya asignada");
        }

        UserMissionEntity entity = new UserMissionEntity();
        entity.setUserId(userId);
        entity.setMissionId(missionId);
        entity.setCompleted(false);

        return userMissionMapper.toDomain(userMissionJpa.save(entity));
    }

    @Transactional
    @Override
    public UserMission completeMission(Long userId, Long missionId) {

        UserMissionEntity userMission = userMissionJpa
                .findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission no asignada"));

        if (userMission.isCompleted()) {
            throw new IllegalArgumentException("Mission ya completada");
        }

        MissionEntity mission = missionJpa.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission no existe"));

        UserEntity user = userJpa.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User no existe"));

        userMission.setCompleted(true);
        userMissionJpa.save(userMission);

        user.setPuntos(user.getPuntos() + mission.getPoints());
        userJpa.save(user);

        return userMissionMapper.toDomain(userMission);
    }
}
