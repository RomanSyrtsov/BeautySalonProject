package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.UserDao;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginProcessCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User userAccount = UserDao.findUser(userName, password);

        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);

            return "/WEB-INF/views/loginView.jsp";
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        //
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        }
            // По умолчанию после успешного входа в систему
            // перенаправить на страницу /userInfo


        return "/WEB-INF/views/mainpage.jsp";
    }
}
