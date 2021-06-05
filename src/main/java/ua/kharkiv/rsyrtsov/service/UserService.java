package ua.kharkiv.rsyrtsov.service;

import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;

public interface UserService {
    User findUserByLoginAndPassw(String login, String password) throws DAOException;

    void registerUser(User user) throws DAOException;

    int getClientIdByLogin(String login) throws DAOException;

    int getMasterIdByLogin(String login) throws DAOException;

    User getUserByRecordId(String recordId) throws DAOException;

}
