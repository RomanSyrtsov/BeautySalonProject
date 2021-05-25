package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.RecordDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewRecordCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Integer clientId = (Integer) session.getAttribute("client_id");
        String masterId = (String) session.getAttribute("masterId");
        session.setAttribute("masterId",null);
        String serviceId = (String) session.getAttribute("service_id");
        session.setAttribute("service_id",null);
        int statusId = 2;
        String date = request.getParameter("dateField");
        System.out.println(date);
        String time = request.getParameter("timeField");
        System.out.println(time);
        RecordDao.insertNewRecord(clientId,masterId,serviceId,statusId,date,time);
        return "controller?command=/";
    }
}
