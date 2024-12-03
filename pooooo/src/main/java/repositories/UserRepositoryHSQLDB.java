package repositories;

import models.User;

import java.util.List;

public class UserRepositoryHSQLDB implements UserRepositoryInterface {
    @Override
    public void save(User user) {
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
