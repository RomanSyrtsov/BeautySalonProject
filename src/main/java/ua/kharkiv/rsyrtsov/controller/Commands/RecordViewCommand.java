package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.ScheduleContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RecordViewCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        List<Master> masters = MasterDao.getMastersByServicesId(Integer.parseInt((String) session.getAttribute("service_id")),(String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(session.getAttribute("masterId") == null){
            ScheduleContainer scheduleContainer = new ScheduleContainer();
            session.setAttribute("masterId",Integer.toString(masters.get(0).getId()));
            scheduleContainer.setSchedules(MasterDao.getMasterScheduleByMasterId(masters.get(0).getId()));
            session.setAttribute("schedules",scheduleContainer);
        }
       /* if(session.getAttribute("dates") == null && session.getAttribute("times") == null){
            List<String> schedules = MasterDao.getMastersScheduleDatesById(masters.get(0).getId());
            session.setAttribute("dates",schedules);
            List<String> times = MasterDao.getMasterScheduleTimesById(masters.get(0).getId());
            session.setAttribute("times",times);
        }*/
        return "/WEB-INF/views/record.jsp";
    }
}
