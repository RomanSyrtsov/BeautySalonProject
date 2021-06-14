package ua.kharkiv.rsyrtsov.controller.Commands.MasterCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.ServiceDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.service.MasterService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MastersListViewCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        HttpSession session = request.getSession();

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        ServiceService serviceService = serviceProvider.getServiceService();

        List<Master> masters = (List<Master>) request.getSession().getAttribute("masters");
        List<Service> services = serviceService.getAllServices((String) session.getAttribute("locale"));

        request.setAttribute("services",services);

        if(masters == null){
            masters = masterService.getAllMasters((String) session.getAttribute("locale"));

        }
        request.getSession().setAttribute("masters", masters);

        return "/WEB-INF/views/masters.jsp";
    }
}
