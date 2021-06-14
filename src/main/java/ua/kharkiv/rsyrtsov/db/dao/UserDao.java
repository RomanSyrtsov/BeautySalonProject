package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;

public interface UserDao {

    User findUser(String userName, String password) throws DAOException;

    int registerUser(User user) throws DAOException;

    int getClientIdByLogin(String login) throws DAOException;

    int getMasterIdByLogin(String login) throws DAOException;

    User getUserByRecordId(String recordId) throws DAOException;

    User getUserById(String userId) throws DAOException;

}
