package com.example.backplayup.user.infrastructure.persistence.jpa;

import com.example.backplayup.user.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
}
