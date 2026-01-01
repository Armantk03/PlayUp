package com.example.backplayup.event.infrastructure.persistence.jpa;

import com.example.backplayup.event.infrastructure.persistence.jpa.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEventRepository extends JpaRepository<EventEntity,Long> {
}
