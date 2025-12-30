package com.example.backplayup.user.domain.port.in;

import com.example.backplayup.user.domain.model.User;

import java.util.Optional;

public interface UserUseCase {

    User create(String nombre, String email, String language) ;
    Optional<User> getByUserId(Long id);
    User changeUserLanguage(Long userId, String language);
    User addPoints(Long userId,int points);
    void deleteUser(Long id);


}
