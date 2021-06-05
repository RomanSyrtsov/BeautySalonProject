package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.impl.ServiceDaoImpl;
import ua.kharkiv.rsyrtsov.db.model.ServiceContainer;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;
import ua.kharkiv.rsyrtsov.service.ServiceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppLocalizationCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ServiceService serviceService = serviceProvider.getServiceService();
        session.removeAttribute("services");
        session.removeAttribute("masters");
        String locale = request.getParameter("locale");
        String previousRequest = (String) session.getAttribute("previous_request");
        session.setAttribute("locale", locale);
        ServiceContainer serviceContainer = new ServiceContainer();
        serviceContainer.setServices(serviceService.getAllServices((locale)));
        session.setAttribute("serviceContainer",serviceContainer);
        System.out.println(previousRequest);
        return previousRequest;
    }
}
