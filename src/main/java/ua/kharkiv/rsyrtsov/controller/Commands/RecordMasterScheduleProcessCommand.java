package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.model.Schedule;
import ua.kharkiv.rsyrtsov.db.model.ScheduleContainer;

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
        String previousRequest = (String)session.getAttribute("previous_request");
        String masterId = request.getParameter("SelectId");
        ScheduleContainer scheduleContainer = new ScheduleContainer();
        if(masterId != null){
            session.setAttribute("masterId",masterId);
            scheduleContainer.setSchedules(MasterDao.getMasterScheduleByMasterId(Integer.parseInt(masterId)));
            session.setAttribute("schedules",scheduleContainer);
        }
        return previousRequest;
    }
}
