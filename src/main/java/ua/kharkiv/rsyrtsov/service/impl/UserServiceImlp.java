package ua.kharkiv.rsyrtsov.service.impl;

import ua.kharkiv.rsyrtsov.db.dao.DAOProvider;
import ua.kharkiv.rsyrtsov.db.dao.UserDao;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.User;
import ua.kharkiv.rsyrtsov.service.UserService;

public class UserServiceImlp implements UserService {

    @Override
    public User findUserByLoginAndPassw(String login, String password) throws DAOException {
        User user;

        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();

        user = userDao.findUser(login, password);

        return user;
    }

    @Override
    public void registerUser(User user) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();

        userDao.registerUser(user);
    }

    @Override
    public int getClientIdByLogin(String login) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        int clientId;

        clientId = userDao.getClientIdByLogin(login);

        return clientId;
    }

    @Override
    public int getMasterIdByLogin(String login) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();
        int masterId;

        masterId = userDao.getMasterIdByLogin(login);

        return masterId;
    }

    @Override
    public User getUserByRecordId(String recordId) throws DAOException {
        User user;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDao userDao = daoProvider.getUserDao();

        user = userDao.getUserByRecordId(recordId);

        return user;
    }
}
