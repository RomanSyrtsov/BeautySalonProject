package ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServicesMakeRecordCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("Record");
        request.getSession().setAttribute("service_id",id);
        request.getSession().setAttribute("masterId",null);
        request.getSession().setAttribute("schedules",null);
        return "controller?command=record";
    }
}
