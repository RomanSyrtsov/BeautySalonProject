package ua.kharkiv.rsyrtsov.service.impl;

import ua.kharkiv.rsyrtsov.db.dao.DAOProvider;
import ua.kharkiv.rsyrtsov.db.dao.RecordDao;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;
import ua.kharkiv.rsyrtsov.service.RecordService;

public class RecordServiceImpl implements RecordService {

    @Override
    public void updateRecordTimeSlot(String date, String time, String recordId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        RecordDao recordDao = daoProvider.getRecordDao();

        recordDao.updateRecordTimeSlot(date, time, recordId);
    }

    @Override
    public void insertNewRecord(int clientId, String masterId, String serviceId, int statusId, String recordDate, String startTime, double service_price, int isPayed) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        RecordDao recordDao = daoProvider.getRecordDao();

        recordDao.insertNewRecord(clientId, masterId, serviceId, statusId, recordDate, startTime, service_price, isPayed);
    }

    @Override
    public void deleteRecordById(String recordId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        RecordDao recordDao = daoProvider.getRecordDao();

        recordDao.deleteRecordById(recordId);
    }

    @Override
    public void updateRecordPaymentById(String recordId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        RecordDao recordDao = daoProvider.getRecordDao();

        recordDao.updateRecordPaymentById(recordId);
    }

    @Override
    public void updateRecordReviewById(String text, String recordId) throws DAOException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        RecordDao recordDao = daoProvider.getRecordDao();

        recordDao.updateRecordReviewById(text, recordId);
    }
}
