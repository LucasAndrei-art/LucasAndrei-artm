package controllers;

import annotations.Inject;
import annotations.Rota;
import annotations.Singleton;

import models.User;
import repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class UserController {
    @Inject
    private UserRepository userRepository;

    @Rota("/user")
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userRepository.findById(userId);
        if (user != null) {
            response.getWriter().write("User: " + user.getFirstName() + " " + user.getLastName());
        } else {
            response.getWriter().write("User not found.");
        }
    }

    @Rota("/user/create")
    public void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = new User(firstName, lastName);
        userRepository.save(user);
        response.getWriter().write("User created with ID: " + user.getId());
    }

    @Rota("/user/update")
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        User user = new User(firstName, lastName);
        user.setId(userId);
        userRepository.update(user);
        response.getWriter().write("User updated.");
    }

    @Rota("/user/delete")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        boolean deleted = userRepository.delete(userId);
        if (deleted) {
            response.getWriter().write("User deleted.");
        } else {
            response.getWriter().write("User not found.");
        }
    }
}