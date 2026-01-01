package com.example.backplayup.event.infrastructure.persistence.jpa.mapper;

import com.example.backplayup.event.domain.model.Event;
import com.example.backplayup.event.infrastructure.persistence.jpa.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event toDomain(EventEntity entity);
    EventEntity toEntity(Event event);

}
