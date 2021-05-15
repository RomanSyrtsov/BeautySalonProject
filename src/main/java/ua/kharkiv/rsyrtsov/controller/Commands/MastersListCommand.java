package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.utils.Sorter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/masters")
public class MastersListCommand extends Command {
    /*List<Master> masters;
    List<Service> services;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/masters.jsp");
        masters = MasterDao.getAllMasters();
        services = ServiceDao.getAllServices();
        request.setAttribute("masters",masters);
        request.setAttribute("services",services);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sorting = request.getParameter("Sorting");
        String filter = request.getParameter("Filter");
        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/masters.jsp");

        if(filter != null){
            masters = MasterDao.getMastersByServicesId(Integer.parseInt(filter));
        }
        if(sorting != null) {

            if (sorting.equals("Sort by rate")) {
                Sorter.sortMastersByRate(masters);
            }
            if (sorting.equals("Sort by names")) {
                Sorter.sortMastersByName(masters);
            }
        }
        request.setAttribute("masters",masters);
        request.setAttribute("services",services);
        dispatcher.forward(request, response);
    }*/

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sorting = request.getParameter("Sorting");
        String filter = request.getParameter("Filter");

        List<Master> masters = (List<Master>) request.getSession().getAttribute("masters");
        List<Service> services = ServiceDao.getAllServices();
        request.setAttribute("services",services);
        if(filter != null){
            if(filter.equals("all")){
                masters = MasterDao.getAllMasters();
            }
            else {
                masters = MasterDao.getMastersByServicesId(Integer.parseInt(filter));
            }
        }
        if(masters == null){
            masters = MasterDao.getAllMasters();
        }
        if(sorting != null) {

            if (sorting.equals("Sort by rate")) {
                Sorter.sortMastersByRate(masters);
            }
            if (sorting.equals("Sort by names")) {
                Sorter.sortMastersByName(masters);
            }

        }
        request.getSession().setAttribute("masters", masters);
        return "/WEB-INF/views/masters.jsp";
    }
}
