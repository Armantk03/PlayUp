package com.example.backplayup.user.domain.port.out;

import com.example.backplayup.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    User createUser(String nombre, String email, String language,String Password);
    User updateUser(User user);
    Optional<User> findById(Long id);
    void deleteById(Long id);
    User changeUserLanguage(Long userId, String language);
    User addPoints(Long userId,int points);
    List<User> getRanking();
    List<User> getTopRanking(int limit);
}
