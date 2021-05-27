package ua.kharkiv.rsyrtsov.controller.Commands.AdminCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.ScheduleContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminRecordsViewCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        List<Master> masters = MasterDao.getAllMasters((String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(session.getAttribute("masterId") == null){
            ScheduleContainer scheduleContainer = new ScheduleContainer();
            session.setAttribute("masterId",Integer.toString(masters.get(0).getId()));
            scheduleContainer.setSchedules(MasterDao.getMasterScheduleByMasterId(masters.get(0).getId()));
            session.setAttribute("schedules",scheduleContainer);
        }
        session.setAttribute("curDate",null);
        return "/WEB-INF/views/adminRecords.jsp";
    }
}
