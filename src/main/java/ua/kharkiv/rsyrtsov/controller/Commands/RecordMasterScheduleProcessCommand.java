package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class RecordMasterScheduleProcessCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String previousRequest = (String) session.getAttribute("previous_request");
        String id = request.getParameter("SelectId");
        String date = request.getParameter("SelectDate");
        String time = request.getParameter("SelectTime");
        List<String> schedules = null;
        if(id != null) {
            session.setAttribute("masterId", id);
        if(session.getAttribute("masterId") != null)
            schedules = MasterDao.getMastersScheduleDatesById(Integer.parseInt(id));
            session.setAttribute("dates", schedules);
        }
        if(date != null){
            session.setAttribute("date",date);
            List<String> times = MasterDao.getMasterScheduleTimesById(Integer.parseInt((String) session.getAttribute("masterId")), date);
            session.setAttribute("times", times);
        }
        if(time != null){
            session.setAttribute("time",time);
        }
        return previousRequest;
    }
}
