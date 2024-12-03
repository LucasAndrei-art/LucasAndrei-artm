package servlet;

import commands.Command;
import commands.CommandRegistry;
import controllers.UserController;

import di.DependencyInjector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/*")
public class CentralServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        try {

            UserController userController = new UserController();

            DependencyInjector.injectDependencies(userController);

            CommandRegistry.initializeRoutes(userController);

            System.out.println("UserController initialized successfully.");
        } catch (Exception e) {

            e.printStackTrace();
            throw new ServletException("Failed to initialize UserController", e);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            String path = req.getPathInfo(); // Obtém a rota requisitada
            Command command = CommandRegistry.getCommand(path); // Busca o comando associado

            if (command != null) {
                command.execute(req, resp); // Executa o comando
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Route not found");
            }
        } catch (Exception e) {
            // Loga qualquer erro durante o processamento da requisição
            e.printStackTrace();
            throw new ServletException("Error processing request", e);
        }
    }
}
