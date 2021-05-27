package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordDao {
    private static final String INSERT_NEW_RECORD = "INSERT INTO record (client_id,master_id,service_id,status_id,record_date,start_time, service_price, isPayed) VALUES (?,?,?,?,?,?,?,?);";

    private static final String UPDATE_RECORD_TIME_SLOT = "UPDATE record SET record_date = ?, start_time = ? WHERE record_id = ?; ";

    private static final  String DELETE_RECORD_BY_ID = "DELETE FROM record WHERE record_id = ?";

    private static final String UPDATE_RECORD_PAYMENT_BY_ID = "UPDATE record SET isPayed = 1 WHERE record_id = ?";

    public static void updateRecordTimeSlot(String date,String time, String recordId){
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_TIME_SLOT);
            preparedStatement.setString(1,date);
            preparedStatement.setString(2,time);
            preparedStatement.setString(3,recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }


    public static void insertNewRecord(int clientId,String masterId,String serviceId,int statusId,String recordDate,String startTime, double service_price, int isPayed){
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_RECORD);
            preparedStatement.setInt(1,clientId);
            preparedStatement.setString(2,masterId);
            preparedStatement.setString(3,serviceId);
            preparedStatement.setInt(4,statusId);
            preparedStatement.setString(5,recordDate);
            preparedStatement.setString(6,startTime);
            preparedStatement.setDouble(7,service_price);
            preparedStatement.setInt(8,isPayed);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public static void deleteRecordById(String recordId) {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECORD_BY_ID);
            preparedStatement.setString(1,recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public static void updateRecordPaymentById(String recordId) {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RECORD_PAYMENT_BY_ID);
            preparedStatement.setString(1,recordId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }
}
