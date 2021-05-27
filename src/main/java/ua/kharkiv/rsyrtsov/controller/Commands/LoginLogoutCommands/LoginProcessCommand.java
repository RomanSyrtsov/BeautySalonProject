package ua.kharkiv.rsyrtsov.controller.Commands.LoginLogoutCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.UserDao;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User userAccount = UserDao.findUser(userName, password);
        if(userAccount.getRoleId() == 1) {
            int clientId = UserDao.getClientIdByLogin(userName);
            request.getSession().setAttribute("client_id", clientId);
        }
        if(userAccount.getRoleId() == 2){
            int masterId = UserDao.getMasterIdByLogin(userName);
            request.getSession().setAttribute("masterId",masterId);
        }
        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);


            return "controller?command=login";
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        return "controller?command=/";
    }
}
