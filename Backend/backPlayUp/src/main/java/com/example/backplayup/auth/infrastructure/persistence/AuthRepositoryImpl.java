package com.example.backplayup.auth.infrastructure.persistence;

import com.example.backplayup.auth.domain.model.Auth;
import com.example.backplayup.auth.domain.model.AuthResult;
import com.example.backplayup.auth.domain.model.Role;
import com.example.backplayup.auth.domain.port.out.AuthRepository;
import com.example.backplayup.auth.infrastructure.persistence.jpa.JpaAuthRepository;
import com.example.backplayup.auth.infrastructure.persistence.jpa.entity.AuthEntity;
import com.example.backplayup.auth.infrastructure.persistence.jpa.mapper.AuthMapper;
import com.example.backplayup.user.infrastructure.persistence.jpa.JpaUserRepository;
import com.example.backplayup.user.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
public class AuthRepositoryImpl implements AuthRepository {

    JpaAuthRepository jpa;
    JpaUserRepository jpaUser;
    AuthMapper mapper;

    public AuthRepositoryImpl(JpaAuthRepository jpa, JpaUserRepository jpaUser, AuthMapper mapper) {
        this.jpa = jpa;
        this.jpaUser = jpaUser;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public Auth register(Long userId, String password) {

        UserEntity user = jpaUser.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User no existe"));

        if (jpa.existsById(userId)) {
            throw new IllegalArgumentException("Auth ya existe para este user");
        }


        BCryptPasswordEncoder be = new BCryptPasswordEncoder();
        String passwordHash = be.encode( password );


        AuthEntity auth = new AuthEntity( userId,passwordHash, Role.USER );

        AuthEntity saved = jpa.save(auth);

        return mapper.toDomain(saved);





    }

    @Override
    public AuthResult login(String email, String password) {
        BCryptPasswordEncoder be = new BCryptPasswordEncoder();

        if (!jpaUser.existsByEmail(email)){

            throw new IllegalArgumentException("El correo no existe");

        }
            UserEntity user = jpaUser.findByEmail(email);

            Long userId = user.getId();

            AuthEntity auth = jpa.findById(userId).orElseThrow(()-> new IllegalArgumentException("No se a encontrado el auth para el user con id :" + userId));

            String passwordHash = auth.getPasswordHash();

            if (!be.matches(password,passwordHash)){
                throw  new IllegalArgumentException("La contraseña dada no coincide con el hash");
            }

        return new AuthResult(auth.getUserId(),auth.getRole());




    }
}
