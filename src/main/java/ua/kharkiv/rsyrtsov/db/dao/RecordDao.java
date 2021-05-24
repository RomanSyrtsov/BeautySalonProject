package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecordDao {
    private static final String INSERT_NEW_RECORD = "INSERT INTO record (client_id,master_id,service_id,status_id,record_date,start_time) VALUES (?,?,?,?,?,?);";
    public static void insertNewRecord(int clientId,String masterId,String serviceId,int statusId,String recordDate,String startTime){
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
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

}
