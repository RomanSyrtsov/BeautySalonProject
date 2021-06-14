package ua.kharkiv.rsyrtsov.db.dao.impl;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.dao.UserDao;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private static final String INSERT_USER_SQL = "INSERT INTO beautysalon.user" +
            "(login,password,email,role_id,firstname,lastname,phone_number) VALUES" +
            "(?, ?, ?, ?,?,?,?);";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD_SQL = "SELECT * FROM beautysalon.user u WHERE u.login = ? AND u.password = ?";
    private static final String SELECT_CLIENT_ID_BY_LOGIN = "SELECT client.client_id " +
            "FROM client INNER JOIN user ON ( client.user_id = user.user_id) " +
            "WHERE (((user.login)=?));";
    private static final String SELECT_MASTER_ID_BY_LOGIN = "SELECT master.master_id " +
            "FROM master INNER JOIN user ON ( master.user_id = user.user_id) " +
            "WHERE (((user.login)=?));";
    private static final String SELECT_USER_BY_RECORD_ID = "SELECT user.user_id, user.login, user.password, user.email, user.role_id, user.firstname, user.lastname, user.phone_number " +
            "FROM (client INNER JOIN record ON client.client_id = record.client_id) INNER JOIN user ON client.user_id = user.user_id " +
            "WHERE (((record.record_id)=?));";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    public User findUser(String userName, String password) throws DAOException {
        Connection connection = null;
        User user = new User();
        try{
            connection = DBManager.getInstance().getConnection();
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
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        if(user.getId()==null){
            user = null;
        }
        return user;
    }

    public int registerUser(User user) throws DAOException {
        int result = 0;
        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
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
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return result;
    }

    public int getClientIdByLogin(String login) throws DAOException {
        int result = 0;
        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_ID_BY_LOGIN);
            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result = rs.getInt("client_id");
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return result;
    }

    public int getMasterIdByLogin(String login) throws DAOException {
        int result = 0;
        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTER_ID_BY_LOGIN);
            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                result = rs.getInt("master_id");
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return result;

    }
    public User getUserByRecordId(String recordId) throws DAOException {
        User user = new User();
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_RECORD_ID);
            preparedStatement.setString(1,recordId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
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
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return user;
    }

    @Override
    public User getUserById(String userId) throws DAOException {
        User user = new User();
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1,userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
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
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return user;
    }
}
