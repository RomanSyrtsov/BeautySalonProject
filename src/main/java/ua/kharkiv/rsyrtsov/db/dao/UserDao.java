package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static final String INSERT_USER_SQL = "INSERT INTO beautysalon.user" +
            "(login,password,email,role_id,firstname,lastname,phone_number) VALUES" +
            "(?, ?, ?, ?,?,?,?);";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD_SQL = "SELECT * FROM beautysalon.user u WHERE u.login = ? AND u.password = ?";

    public static User findUser(String userName, String password) {

        User user = new User();
        try (Connection connection = DBManager.getConnectionWithDriverManager()){

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD_SQL);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("user_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoleId(rs.getInt("role_id"));
                user.setFirstname(rs.getString("firstname"));
                user.setLastname(rs.getString("lastname"));
                user.setPhone_number(rs.getString("phone_number"));

            }
            rs.close();
            preparedStatement.close();
            DBManager.commitAndClose(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static int registerUser(User user){
        int result = 0;
        try (Connection connection = DBManager.getConnectionWithDriverManager()){
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setInt(4,user.getRoleId());
            preparedStatement.setString(5,user.getFirstname());
            preparedStatement.setString(6,user.getLastname());
            preparedStatement.setString(7,user.getPhone_number());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            DBManager.commitAndClose(connection);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
