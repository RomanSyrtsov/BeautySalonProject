package ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.db.model.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ServicesListViewCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Service> serviceList = (List<Service>) request.getSession().getAttribute("services");
        HttpSession session = request.getSession();
        List<Master>masters = MasterDao.getAllMasters((String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(serviceList == null) {
            serviceList = ServiceDao.getAllServices((String) session.getAttribute("locale"));
            ServiceContainer serviceContainer = new ServiceContainer();
            serviceContainer.setServices(serviceList);
            session.setAttribute("serviceContainer",serviceContainer);
            request.getSession().setAttribute("services", serviceList);
        }
        return "/WEB-INF/views/services.jsp";
    }
}
