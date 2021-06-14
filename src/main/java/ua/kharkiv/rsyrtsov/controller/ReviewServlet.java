package ua.kharkiv.rsyrtsov.controller;

import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.RecordDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.service.RecordService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String recordId = request.getParameter("recordId");
        HttpSession session = request.getSession();
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
        request.setAttribute("userId",userId);
        request.setAttribute("recordId",recordId);
        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/views/review.jsp");
        disp.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RecordService recordService = serviceProvider.getRecordService();
        String recordId = request.getParameter("recordIdField");
        String text = request.getParameter("reviewText");

        try {
            recordService.updateRecordReviewById(text,recordId);
        } catch (DAOException e) {
            request.setAttribute("error", e);
            request.getSession().setAttribute("error", e);
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
        response.sendRedirect("controller?command=/");
    }
}
