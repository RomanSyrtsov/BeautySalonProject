package ua.kharkiv.rsyrtsov.controller.Commands.LoginLogoutCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.UserDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone_number = request.getParameter("phone_number");

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoleId(1);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setPhone_number(phone_number);

        userService.registerUser(user);

        return "controller?command=/";
    }
}
