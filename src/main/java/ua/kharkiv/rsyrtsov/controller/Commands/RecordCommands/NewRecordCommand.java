package ua.kharkiv.rsyrtsov.controller.Commands.RecordCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.RecordDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.ServiceContainer;
import ua.kharkiv.rsyrtsov.service.RecordService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewRecordCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {

        HttpSession session = request.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RecordService recordService = serviceProvider.getRecordService();
        Integer clientId = (Integer) session.getAttribute("client_id");
        String masterId = (String) session.getAttribute("masterId");
        session.setAttribute("masterId",null);
        String serviceId = (String) session.getAttribute("service_id");
        session.setAttribute("service_id",null);
        int statusId = 2;
        String date = request.getParameter("dateField");
        String time = request.getParameter("timeField");
        ServiceContainer serviceContainer = (ServiceContainer) session.getAttribute("serviceContainer");
        double price = serviceContainer.getServicePriceById(Long.parseLong(serviceId));
        recordService.insertNewRecord(clientId,masterId,serviceId,statusId,date,time,price,0);
        return "controller?command=services";
    }
}
