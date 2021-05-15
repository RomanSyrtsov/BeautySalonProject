package ua.kharkiv.rsyrtsov.controller.Commands;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/")
public class MainCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return "/WEB-INF/views/mainpage.jsp";
    }
}
