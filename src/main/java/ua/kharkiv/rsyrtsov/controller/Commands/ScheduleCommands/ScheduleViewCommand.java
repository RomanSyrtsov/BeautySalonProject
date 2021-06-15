package ua.kharkiv.rsyrtsov.controller.Commands.ScheduleCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.MasterDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.RecordContainer;
import ua.kharkiv.rsyrtsov.service.MasterService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ScheduleViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        HttpSession session = request.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        Integer masterId = (Integer) session.getAttribute("masterId");
        if(session.getAttribute("masterId") != null){
            RecordContainer scheduleContainer = new RecordContainer();
            scheduleContainer.setSchedules(masterService.getMasterScheduleByMasterId(masterId));
            session.setAttribute("schedules",scheduleContainer);
        }
        return "/WEB-INF/views/schedule.jsp";
    }
}
