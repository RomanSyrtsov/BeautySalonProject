package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.utils.Sorter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class MasterListProcessCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sorting = request.getParameter("Sorting");
        String filter = request.getParameter("Filter");
        HttpSession session = request.getSession();
        List<Master> masters = (List<Master>) request.getSession().getAttribute("masters");
        List<Service> services = ServiceDao.getAllServices((String) session.getAttribute("locale"));
        if(filter != null){
            if(filter.equals("all")){
                masters = MasterDao.getAllMasters((String) session.getAttribute("locale"));
            }
            else {
                masters = MasterDao.getMastersByServicesId(Integer.parseInt(filter),(String) session.getAttribute("locale"));
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
