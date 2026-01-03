package com.example.backplayup.user.application;

import com.example.backplayup.user.domain.model.User;
import com.example.backplayup.user.domain.port.in.UserUseCase;
import com.example.backplayup.user.domain.port.out.UserRepository;
import com.example.backplayup.user.infrastructure.persistence.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    UserRepository repo;

    public UserService(UserRepositoryImpl repo) {
        this.repo = repo;
    }


    @Override
    public User create(String nombre, String email, String language,String password) {
        return repo.createUser(nombre,email,language,password);
    }

    @Override
    public Optional<User> getByUserId(Long id) {
        return repo.findById(id);
    }

    @Override
    public User changeUserLanguage(Long userId, String language) {
        return repo.changeUserLanguage(userId,language);
    }

    @Override
    public User addPoints(Long userId, int points) {
        return repo.addPoints(userId,points);
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<User> getRanking() {
        return repo.getRanking();
    }

    @Override
    public List<User> getTopRanking(int limit) {
        return repo.getTopRanking(limit);
    }

}
