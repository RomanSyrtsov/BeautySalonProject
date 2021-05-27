package ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.model.ScheduleContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminChangeTimeSlotFormView implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String masterId = (String) session.getAttribute("masterId");
        if(session.getAttribute("masterId") != null){
            ScheduleContainer scheduleContainer = new ScheduleContainer();
            scheduleContainer.setSchedules(MasterDao.getMasterScheduleByMasterId(Integer.parseInt(masterId)));
            req.setAttribute("schedules1",scheduleContainer);
        }
        return "/WEB-INF/views/changeTimeSlotForm.jsp";
    }
}
