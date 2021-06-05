package ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.db.model.ServiceContainer;
import ua.kharkiv.rsyrtsov.service.MasterService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ServicesListViewCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        List<Service> serviceList = (List<Service>) request.getSession().getAttribute("services");
        HttpSession session = request.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        ServiceService serviceService = serviceProvider.getServiceService();
        List<Master>masters = masterService.getAllMasters((String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(serviceList == null) {
            serviceList = serviceService.getAllServices((String) session.getAttribute("locale"));
            ServiceContainer serviceContainer = new ServiceContainer();
            serviceContainer.setServices(serviceList);
            session.setAttribute("serviceContainer",serviceContainer);
            request.getSession().setAttribute("services", serviceList);
        }
        return "/WEB-INF/views/services.jsp";
    }
}
