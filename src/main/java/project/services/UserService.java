package project.services;

import project.domain.User;

import java.util.List;

public interface UserService {
    void save(User user);
    User findById(Long chatId);
    boolean existsById(Long chatId);
    List<User> findAll();
    void delete(User user);
}
