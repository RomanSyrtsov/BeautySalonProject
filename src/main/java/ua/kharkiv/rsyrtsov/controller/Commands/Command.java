package ua.kharkiv.rsyrtsov.controller.Commands;

import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public interface Command {

    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, DAOException;
}


