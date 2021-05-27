package ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.RecordDao;
import ua.kharkiv.rsyrtsov.db.model.ScheduleContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminRecordsProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String command = request.getQueryString();
        String previousRequest = (String)session.getAttribute("previous_request");
        String recordId = request.getParameter("recordIdField");
        if(command.equals("command=changeTimeSlot")){
            session.setAttribute("changeRecordId",recordId);
            return "controller?command=changeTimeSlotForm";
        }
        if(command.equals("command=cancelStatus")){
            RecordDao.deleteRecordById(recordId);
            session.setAttribute("masterId",null);
        }
        if(command.equals("command=submitPayment")){
            RecordDao.updateRecordPaymentById(recordId);
            session.setAttribute("masterId",null);
        }
        return previousRequest;
    }
}
