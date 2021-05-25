package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ServicesListViewCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Service> serviceList = (List<Service>) request.getSession().getAttribute("services");
        HttpSession session = request.getSession();
        List<Master>masters = MasterDao.getAllMasters((String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(serviceList == null) {
            serviceList = ServiceDao.getAllServices((String) session.getAttribute("locale"));
            request.getSession().setAttribute("services", serviceList);
        }
        return "/WEB-INF/views/services.jsp";
    }
}
