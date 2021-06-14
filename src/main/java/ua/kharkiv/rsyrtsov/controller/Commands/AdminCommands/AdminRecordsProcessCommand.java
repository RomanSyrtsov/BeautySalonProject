package ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.service.RecordService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.UserService;
import ua.kharkiv.rsyrtsov.utils.Mailer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AdminRecordsProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        HttpSession session = request.getSession();
        String command = request.getQueryString();
        String previousRequest = (String) session.getAttribute("previous_request");
        String recordId = request.getParameter("recordIdField");
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        RecordService recordService = serviceProvider.getRecordService();
        if (command.equals("command=changeTimeSlot")) {
            session.setAttribute("changeRecordId", recordId);
            return "controller?command=changeTimeSlotForm";
        }
        if (command.equals("command=cancelStatus")) {
            recordService.deleteRecordById(recordId);
            session.setAttribute("masterId", null);
        }
        if (command.equals("command=submitPayment")) {
            recordService.updateRecordPaymentById(recordId);
            session.setAttribute("masterId", null);
            User user = userService.getUserByRecordId(recordId);
            String to="sadchatstories@gmail.com";
            Long id = user.getId();

            Mailer.send(to,id,recordId);
        }
        return previousRequest;
    }
}
