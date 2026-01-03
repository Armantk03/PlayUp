package com.example.backplayup.mission.infrastructure.persistence.jpa.mapper;

import com.example.backplayup.mission.domain.model.UserMission;
import com.example.backplayup.mission.infrastructure.persistence.jpa.entity.UserMissionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMissionMapper {
    UserMission toDomain(UserMissionEntity entity);
}
