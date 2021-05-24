package ua.kharkiv.rsyrtsov.controller.Commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RecordCheckTimeCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String previousRequest = (String) session.getAttribute("previous_request");
        String time = request.getParameter("SelectTime");
        String date = request.getParameter("SelectDate");
        String id = request.getParameter("SelectDate");
        System.out.println(time);
        System.out.println(date);
        return previousRequest;
    }
}
