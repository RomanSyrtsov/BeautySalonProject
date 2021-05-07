package ua.kharkiv.rsyrtsov.controller;

import ua.kharkiv.rsyrtsov.db.dao.UserDao;
import ua.kharkiv.rsyrtsov.db.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userregister.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setRoleId(1);

        userDao.registerUser(user);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
