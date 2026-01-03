package com.example.backplayup.auth.infrastructure.persistence.jpa.mapper;

import com.example.backplayup.auth.domain.model.Auth;
import com.example.backplayup.auth.infrastructure.persistence.jpa.entity.AuthEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    Auth toDomain(AuthEntity entity);
    AuthEntity toEntity(Auth auth);

}
