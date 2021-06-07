package ua.kharkiv.rsyrtsov.controller.Commands.LoginLogoutCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.UserService;
import ua.kharkiv.rsyrtsov.utils.AppUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        User userAccount = userService.findUserByLoginAndPassw(userName, password);
        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);


            return "controller?command=login";
        }
        if (userAccount.getRoleId() == 1) {
            int clientId = userService.getClientIdByLogin(userName);
            request.getSession().setAttribute("client_id", clientId);
        }
        if (userAccount.getRoleId() == 2) {
            int masterId = userService.getMasterIdByLogin(userName);
            request.getSession().setAttribute("masterId", masterId);
        }


        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        return "controller?command=/";
    }
}
