package ua.kharkiv.rsyrtsov.service.impl;

import ua.kharkiv.rsyrtsov.db.dao.DAOProvider;
import ua.kharkiv.rsyrtsov.db.dao.MasterDao;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Record;
import ua.kharkiv.rsyrtsov.service.MasterService;

import java.util.List;

public class MasterServiceImpl implements MasterService {
    @Override
    public List<Master> getAllMasters(String locale) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        MasterDao masterDao = daoProvider.getMasterDao();

        List<Master> masters;

            masters = masterDao.getAllMasters(locale);


        return masters;
    }

    @Override
    public List<Master> getMastersByServicesId(int id, String locale) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        MasterDao masterDao = daoProvider.getMasterDao();
        List<Master> masters;

            masters = masterDao.getMastersByServicesId(id, locale);

        return masters;
    }

    @Override
    public List<Record> getMasterScheduleByMasterId(int id) throws DAOException {
        List<Record> records;
        DAOProvider daoProvider = DAOProvider.getInstance();
        MasterDao masterDao = daoProvider.getMasterDao();

            records = masterDao.getMasterScheduleByMasterId(id);

        return records;
    }

    @Override
    public void updateStatusId(String recordId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        MasterDao masterDao = daoProvider.getMasterDao();

            masterDao.updateStatusId(recordId);

    }
}
