package ua.kharkiv.rsyrtsov.controller.Commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServicesMakeRecordCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("Record");
        request.getSession().setAttribute("service_id",id);
        return "controller?command=record";
    }
}
