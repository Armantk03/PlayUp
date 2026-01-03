package com.example.backplayup.mission.infrastructure.persistence.jpa;

import com.example.backplayup.mission.infrastructure.persistence.jpa.entity.UserMissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserMissionRepository extends JpaRepository<UserMissionEntity, Long> {

    Optional<UserMissionEntity> findByUserIdAndMissionId(Long userId, Long missionId);
}
