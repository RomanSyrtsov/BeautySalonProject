package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppLocalizationCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("services");
        session.removeAttribute("masters");
        String locale = request.getParameter("locale");
        String previousRequest = (String) session.getAttribute("previous_request");
        session.setAttribute("locale", locale);
        ServiceContainer serviceContainer = new ServiceContainer();
        serviceContainer.setServices(ServiceDao.getAllServices((locale)));
        session.setAttribute("serviceContainer",serviceContainer);
        System.out.println(previousRequest);
        return previousRequest;
    }
}
