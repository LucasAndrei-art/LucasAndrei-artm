package repositories;

import annotations.Singleton;

import java.util.HashMap;
import java.util.Map;

import models.User;

@Singleton
public class UserRepository {
    private static UserRepository instance;
    private final Map<Integer, User> users;
    private int idCounter;

    private UserRepository() {
        this.users = new HashMap<>();
        this.idCounter = 1;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(idCounter++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public User findById(int id) {
        return users.get(id);
    }

    public Map<Integer, User> findAll() {
        return users;
    }

    public User update(User user) {
        if (users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            return user;
        }
        return null;
    }


    public boolean delete(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return true;
        }
        return false;
    }
}