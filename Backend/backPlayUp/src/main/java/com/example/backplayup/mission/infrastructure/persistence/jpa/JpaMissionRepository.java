package com.example.backplayup.mission.infrastructure.persistence.jpa;

import com.example.backplayup.mission.infrastructure.persistence.jpa.entity.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMissionRepository extends JpaRepository<MissionEntity, Long> {
}
