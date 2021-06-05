package ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands;

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

public class AdminChangeTimeSlotFormView implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, DAOException {
        HttpSession session = req.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        String masterId = (String) session.getAttribute("masterId");
        if(session.getAttribute("masterId") != null){
            RecordContainer scheduleContainer = new RecordContainer();
            scheduleContainer.setSchedules(masterService.getMasterScheduleByMasterId(Integer.parseInt(masterId)));
            req.setAttribute("schedules1",scheduleContainer);
        }
        return "/WEB-INF/views/changeTimeSlotForm.jsp";
    }
}
