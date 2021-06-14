package ua.kharkiv.rsyrtsov.controller.ReviewCommands;

import ua.kharkiv.rsyrtsov.controller.Commands.Command;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.service.RecordService;
import ua.kharkiv.rsyrtsov.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReviewProcess implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, DAOException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        RecordService recordService = serviceProvider.getRecordService();
        String recordId = req.getParameter("recordIdField");
        String text = req.getParameter("reviewText");

        recordService.updateRecordReviewById(text,recordId);

        return "controller?command=/";
    }
}
