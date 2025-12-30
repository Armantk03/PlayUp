package com.example.backplayup.user.infrastructure.persistence.jpa.mapper;

import com.example.backplayup.user.domain.model.User;
import com.example.backplayup.user.infrastructure.persistence.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity entity);
    UserEntity toEntity(User model);

}
