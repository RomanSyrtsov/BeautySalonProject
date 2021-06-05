package ua.kharkiv.rsyrtsov.controller.Commands.ServicesCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.ServiceDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.service.MasterService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ServicesListProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        String id = request.getParameter("Sorting");
        HttpSession session = request.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        ServiceService serviceService = serviceProvider.getServiceService();
        if(id != null && !id.equals("1L")){
            session.setAttribute("master_id", id);
        }else {
            session.setAttribute("master_id",null);
        }
        List<Service> serviceList;
        List<Master>masters = masterService.getAllMasters((String) session.getAttribute("locale"));
        request.setAttribute("masters",masters);
        if(id.equals("1L")){
            serviceList = serviceService.getAllServices((String) session.getAttribute("locale"));
        }
        else {
            serviceList = serviceService.getServiceByMasterId(Integer.parseInt(id),(String) session.getAttribute("locale"));
        }
        request.getSession().setAttribute("services",serviceList);
        return "controller?command=services";
    }
}
