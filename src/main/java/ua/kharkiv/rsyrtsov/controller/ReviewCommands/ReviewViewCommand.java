package ua.kharkiv.rsyrtsov.controller.ReviewCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReviewViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, DAOException {
        String userId = req.getParameter("userId");
        String recordId = req.getParameter("recordId");
        HttpSession session = req.getSession();
        System.out.println(userId + " " + recordId);
        User user = null;
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            user = userService.getUserById(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        session.setAttribute("loginedUser",user);
        req.setAttribute("userId",userId);
        req.setAttribute("recordId",recordId);

        return "/WEB-INF/views/review.jsp";
    }
}
