package repositories;

import models.User;

import java.util.List;

public interface UserRepositoryInterface {
    void save(User user);
    List<User> findAll();
}
