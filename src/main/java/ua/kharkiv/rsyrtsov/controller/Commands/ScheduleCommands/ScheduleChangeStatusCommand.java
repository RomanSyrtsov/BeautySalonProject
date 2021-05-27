package ua.kharkiv.rsyrtsov.controller.Commands.ScheduleCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.MasterDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ScheduleChangeStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String previousRequest = (String)session.getAttribute("previous_request");
        String recordId = request.getParameter("recordIdField");
        MasterDao.updateStatusId(recordId);
        return previousRequest;
    }
}
