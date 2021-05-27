package ua.kharkiv.rsyrtsov.controller.Commands.LoginLogoutCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return "/WEB-INF/views/loginView.jsp";
    }
}
