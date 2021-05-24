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
   /* List<Master> masters;
    List<Service> services;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/services.jsp");
        services = ServiceDao.getAllServices();
        masters = MasterDao.getAllMasters();
        request.setAttribute("services",services);
        request.setAttribute("masters",masters);
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("Sorting");
        if(id.equals("all")){
            doGet(request,response);
        }
            List<Service> serviceList = ServiceDao.getServiceByMasterId(Integer.parseInt(id));
            request.setAttribute("services", serviceList);
            request.setAttribute("masters", masters);
        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/services.jsp");
        dispatcher.forward(request, response);
    }*/

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
