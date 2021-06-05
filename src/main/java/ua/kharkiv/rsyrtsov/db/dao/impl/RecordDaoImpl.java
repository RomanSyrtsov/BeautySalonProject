package ua.kharkiv.rsyrtsov.db.dao.impl;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.dao.RecordDao;
import ua.kharkiv.rsyrtsov.db.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordDaoImpl implements RecordDao {
    private static final String INSERT_NEW_RECORD = "INSERT INTO record (client_id,master_id,service_id,status_id,record_date,start_time, service_price, isPayed) VALUES (?,?,?,?,?,?,?,?);";

    private static final String UPDATE_RECORD_TIME_SLOT = "UPDATE record SET record_date = ?, start_time = ? WHERE record_id = ?; ";

    private static final String DELETE_RECORD_BY_ID = "DELETE FROM record WHERE record_id = ?";

    private static final String UPDATE_RECORD_PAYMENT_BY_ID = "UPDATE record SET isPayed = 1 WHERE record_id = ?";

    private static final String UPDATE_RECORD_REVIEW_BY_ID = "UPDATE record SET review = ? WHERE record_id = ?";

    public void updateRecordTimeSlot(String date, String time, String recordId) throws DAOException {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_TIME_SLOT);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }


    public void insertNewRecord(int clientId, String masterId, String serviceId, int statusId, String recordDate, String startTime, double service_price, int isPayed) throws DAOException {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_RECORD);
            preparedStatement.setInt(1, clientId);
            preparedStatement.setString(2, masterId);
            preparedStatement.setString(3, serviceId);
            preparedStatement.setInt(4, statusId);
            preparedStatement.setString(5, recordDate);
            preparedStatement.setString(6, startTime);
            preparedStatement.setDouble(7, service_price);
            preparedStatement.setInt(8, isPayed);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void deleteRecordById(String recordId) throws DAOException {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECORD_BY_ID);
            preparedStatement.setString(1, recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void updateRecordPaymentById(String recordId) throws DAOException {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_PAYMENT_BY_ID);
            preparedStatement.setString(1, recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void updateRecordReviewById(String text, String recordId) throws DAOException {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_REVIEW_BY_ID);
            preparedStatement.setString(1,text);
            preparedStatement.setString(2,recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            throw new DAOException(e);
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

}
