package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.dao.impl.MasterDaoImpl;
import ua.kharkiv.rsyrtsov.db.dao.impl.RecordDaoImpl;
import ua.kharkiv.rsyrtsov.db.dao.impl.ServiceDaoImpl;
import ua.kharkiv.rsyrtsov.db.dao.impl.UserDaoImpl;

public class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final UserDao userDao = new UserDaoImpl();

    private final MasterDao masterDao = new MasterDaoImpl();

    private final ServiceDao serviceDao = new ServiceDaoImpl();

    private final RecordDao recordDao = new RecordDaoImpl();

    private DAOProvider() {

    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public MasterDao getMasterDao() {
        return masterDao;
    }

    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    public RecordDao getRecordDao(){
        return recordDao;
    }

}
