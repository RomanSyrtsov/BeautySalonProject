package ua.kharkiv.rsyrtsov.controller.Commands.MasterCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.dao.impl.ServiceDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.service.MasterService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.ServiceService;
import ua.kharkiv.rsyrtsov.utils.Sorter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MasterListProcessCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        String sorting = request.getParameter("Sorting");
        String filter = request.getParameter("Filter");
        HttpSession session = request.getSession();

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        MasterService masterService = serviceProvider.getMasterService();
        ServiceService serviceService = serviceProvider.getServiceService();

        List<Master> masters = (List<Master>) request.getSession().getAttribute("masters");
        List<Service> services = serviceService.getAllServices((String) session.getAttribute("locale"));

        if(filter != null){
            if(filter.equals("all")){
                masters = masterService.getAllMasters((String) session.getAttribute("locale"));
                session.setAttribute("serviceId", null);
            }
            else {
                masters = masterService.getMastersByServicesId(Integer.parseInt(filter),(String) session.getAttribute("locale"));
                session.setAttribute("serviceId", filter);
            }
        }
        if(sorting != null) {

            if (sorting.equals("Sort by rate")) {
                Sorter.sortMastersByRate(masters);
            }
            if (sorting.equals("Sort by names")) {
                Sorter.sortMastersByName(masters);
            }

        }
        request.getSession().setAttribute("masters",masters);
        return "controller?command=masters";
    }
}
