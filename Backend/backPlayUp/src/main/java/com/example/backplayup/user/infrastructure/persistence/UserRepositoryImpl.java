package com.example.backplayup.user.infrastructure.persistence;

import com.example.backplayup.user.domain.model.User;
import com.example.backplayup.user.domain.port.out.UserRepository;
import com.example.backplayup.user.infrastructure.persistence.jpa.JpaUserRepository;

import com.example.backplayup.user.infrastructure.persistence.jpa.entity.UserEntity;
import com.example.backplayup.user.infrastructure.persistence.jpa.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;
@Component
public class UserRepositoryImpl implements UserRepository {


    private JpaUserRepository jpa;
    private UserMapper mapper;

    public UserRepositoryImpl(JpaUserRepository jpa, UserMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public User createUser(String name, String email, String language) {

        User user = new User();
        if (jpa.existsByEmail(email)) {
            throw new IllegalArgumentException("Este correo ya está registrado");
        }


           user.setNombre(name);
           user.setEmail(email);
           user.setNivel(1);
           user.setPuntos(0);
           user.setRegistro(LocalDateTime.now());

           if (language==null || language.isBlank()){
               user.setLanguage("es");
           }else {
               user.setLanguage(language);
           }

        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpa.save(entity);

        return mapper.toDomain(saved);


    }



    @Transactional
    @Override
    public User updateUser(User user) {
        UserEntity entity = jpa.findById(user.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuario no existe"));

        entity.setNombre(user.getNombre());

        UserEntity saved = jpa.save(entity);


        return mapper.toDomain(saved);
    }
    @Transactional
    public User changeUserLanguage(Long id, String languange){
        UserEntity entity = jpa.findById(id).orElseThrow(() ->new IllegalArgumentException("Usuario no existe"));

        if (languange == null || languange.isBlank()) {
            throw new IllegalArgumentException("Idioma no válido");
        }


        entity.setLanguage(languange);
        UserEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }
    @Transactional
    public User addPoints(Long id, int points) {

        if (points <= 0) {
            throw new IllegalArgumentException("Los puntos deben ser positivos");
        }

        UserEntity entity = jpa.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));

        entity.setPuntos(entity.getPuntos() + points);

        UserEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Transactional
    @Override
    public Optional<User> findById(Long id) {
        return jpa.findById(id)
                .map(mapper::toDomain);
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }


}
