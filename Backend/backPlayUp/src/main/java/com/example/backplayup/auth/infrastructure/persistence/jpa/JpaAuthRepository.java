package com.example.backplayup.auth.infrastructure.persistence.jpa;

import com.example.backplayup.auth.infrastructure.persistence.jpa.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAuthRepository extends JpaRepository<AuthEntity,Long> {
}
