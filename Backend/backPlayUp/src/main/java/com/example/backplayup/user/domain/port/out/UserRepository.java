package com.example.backplayup.user.domain.port.out;

import com.example.backplayup.user.domain.model.User;

import java.util.Optional;

public interface UserRepository {


    User createUser(String nombre, String email, String language);
    User updateUser(User user);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
