package ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.RecordDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminChangeTimeSlotFormProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String previousRequest = (String)session.getAttribute("previous_request");
        String date = req.getParameter("SelectDate");
        String time = req.getParameter("SelectTime");
        if(date != null && time == null) {
            session.setAttribute("curDate",date);
            return previousRequest;
        }
        if(session.getAttribute("curDate")!= null && time != null) {
            String recordId = (String) session.getAttribute("changeRecordId");
            String curDate = (String) session.getAttribute("curDate");
            RecordDao.updateRecordTimeSlot(curDate, time,recordId);
            session.setAttribute("masterId",null);
        }
        return "controller?command=adminRecords";
    }
}
