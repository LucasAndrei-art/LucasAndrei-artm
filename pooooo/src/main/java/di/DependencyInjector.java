package di;

import annotations.Inject;
import models.User;
import repositories.Repository;

import java.lang.reflect.Field;
import java.util.List;

public class DependencyInjector {

    public static void injectDependencies(Object controller) throws IllegalAccessException {
        for (Field field : controller.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);

                field.set(controller, new Repository<User>() {
                    @Override
                    public void save(User entity) {

                    }

                    @Override
                    public List<User> findAll() {
                        return List.of();
                    }
                });
            }
        }
    }
}
