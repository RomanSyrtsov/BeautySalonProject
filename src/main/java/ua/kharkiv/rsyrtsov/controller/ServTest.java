package ua.kharkiv.rsyrtsov.controller;

import ua.kharkiv.rsyrtsov.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/hello")
public class ServTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();



        out.print("<h1>Hello Servlet</h1>");
        try {
            for(User user : testMain.getAllUsers()){
                out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
