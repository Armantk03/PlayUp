package com.example.backplayup.user.domain.port.in;

import com.example.backplayup.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserUseCase {

    User create(String nombre, String email, String language,String Password) ;
    Optional<User> getByUserId(Long id);
    User changeUserLanguage(Long userId, String language);
    User addPoints(Long userId,int points);
    void deleteUser(Long id);
    List<User> getRanking();
    List<User> getTopRanking(int limit);


}
