package com.example.backplayup.mission.infrastructure.persistence.jpa.mapper;

import com.example.backplayup.mission.domain.model.Mission;
import com.example.backplayup.mission.infrastructure.persistence.jpa.entity.MissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MissionMapper {
    Mission toDomain(MissionEntity entity);
    MissionEntity toEntity(Mission model);
}