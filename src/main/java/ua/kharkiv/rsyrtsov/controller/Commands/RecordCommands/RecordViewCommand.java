package ua.kharkiv.rsyrtsov.controller.Commands.RecordCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.MasterDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.RecordContainer;
import ua.kharkiv.rsyrtsov.service.MasterService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RecordViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        HttpSession session = request.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        List<Master> masters = masterService.getMastersByServicesId(Integer.parseInt((String) session.getAttribute("service_id")),(String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(session.getAttribute("masterId") == null){
            RecordContainer scheduleContainer = new RecordContainer();
            session.setAttribute("masterId",Integer.toString(masters.get(0).getId()));
            scheduleContainer.setSchedules(masterService.getMasterScheduleByMasterId(masters.get(0).getId()));
            session.setAttribute("schedules",scheduleContainer);
        }
        return "/WEB-INF/views/record.jsp";
    }
}
