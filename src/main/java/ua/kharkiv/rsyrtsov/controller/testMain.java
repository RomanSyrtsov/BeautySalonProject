package ua.kharkiv.rsyrtsov.controller;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testMain {
    public static List<User> getAllUsers() throws SQLException {
        DBManager dbManager = new DBManager();
        List<User> users = new ArrayList<>();
        Connection con = dbManager.getConnectionWithDriverManager();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user u order by u.login;");
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRoleId(rs.getInt("role_id"));
            users.add(user);
        }
        rs.close();
        stmt.close();
        con.close();
        return users;
    }
    public static void main(String[] args) throws SQLException {
        for (User user : getAllUsers()){
            System.out.println(user);
        }
    }

}
