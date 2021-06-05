package ua.kharkiv.rsyrtsov.controller.Commands.RecordCommands;

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

public class RecordMasterScheduleProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        HttpSession session = request.getSession();
        String previousRequest = (String)session.getAttribute("previous_request");
        String masterId = request.getParameter("SelectId");
        RecordContainer scheduleContainer = new RecordContainer();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        if(masterId != null){
            session.setAttribute("masterId",masterId);
            scheduleContainer.setSchedules(masterService.getMasterScheduleByMasterId(Integer.parseInt(masterId)));
            session.setAttribute("schedules",scheduleContainer);
        }
        return previousRequest;
    }
}
