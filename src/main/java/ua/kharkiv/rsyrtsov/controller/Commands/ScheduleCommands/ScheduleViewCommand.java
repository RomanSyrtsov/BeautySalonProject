package ua.kharkiv.rsyrtsov.controller.Commands.ScheduleCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.model.ScheduleContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ScheduleViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Integer masterId = (Integer) session.getAttribute("masterId");
        System.out.println(masterId);
        if(session.getAttribute("masterId") != null){
            ScheduleContainer scheduleContainer = new ScheduleContainer();
            scheduleContainer.setSchedules(MasterDao.getMasterScheduleByMasterId(masterId));
            session.setAttribute("schedules",scheduleContainer);
        }
        return "/WEB-INF/views/schedule.jsp";
    }
}
