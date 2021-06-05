package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;

public interface RecordDao {

    void updateRecordTimeSlot(String date, String time, String recordId) throws DAOException;

    void insertNewRecord(int clientId, String masterId, String serviceId, int statusId, String recordDate, String startTime, double service_price, int isPayed) throws DAOException;

    void deleteRecordById(String recordId) throws DAOException;

    void updateRecordPaymentById(String recordId) throws DAOException;

    void updateRecordReviewById(String text, String recordId) throws DAOException;

}
