package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MasterDao {

    /*private static final String SELECT_SCHEDULE_RECORD_ID_BY_MASTER_ID = "SELECT schedule.record_id\n" +
            "FROM master INNER JOIN schedule ON master.master_id = schedule.master_id\n" +
            "WHERE (((schedule.master_id)=?) AND ((schedule.date)=?));";

    private static final String SELECT_MASTER_SCHEDULE_DATES_BY_MASTER_ID = "SELECT schedule.date " +
            "FROM (master INNER JOIN master_resource ON master.master_id = master_resource.master_id) INNER JOIN schedule ON master.master_id = schedule.master_id " +
            "GROUP BY schedule.date, master.master_id " +
            "HAVING (((master.master_id)=?));";
    private static final String SELECT_MASTER_SCHEDULE_TIMES_BY_MASTER_ID = "SELECT schedule.time " +
            "FROM schedule\n" +
            "GROUP BY schedule.time, schedule.master_id, schedule.date, schedule.record_id " +
            "HAVING (((schedule.master_id)=?) AND ((schedule.date)=?) AND ((schedule.record_id) is NULL));";*/
    private static final String SELECT_MASTER_SCHEDULE_BY_MASTER_ID = "SELECT schedule.time, schedule.date, schedule.record_id " +
            "FROM schedule " +
            "WHERE (((schedule.master_id)=?)) " +
            "ORDER BY schedule.date,schedule.time ASC;\n";

    private static final String SELECT_ALL_MASTERS = "SELECT master.master_id, master_resource.firstname, master_resource.lastname, master.phone_number, speciality_resource.speciality_name, master.master_rate, master.user_id " +
            "FROM (specialty INNER JOIN (master LEFT JOIN master_resource ON master.master_id = master_resource.master_id) ON specialty.speciality_id = master.speciality_id) INNER JOIN speciality_resource ON specialty.speciality_id = speciality_resource.speciality_id " +
            "WHERE (((master_resource.locale)=?) AND ((speciality_resource.locale)=?));";
    private static final String SELECT_MASTERS_BY_SERVICES_ID = "SELECT master.master_id, master_resource.firstname, master_resource.lastname, master.phone_number, speciality_resource.speciality_name, master.master_rate, master.user_id " +
            "FROM ((specialty INNER JOIN (master INNER JOIN master_resource ON master.master_id = master_resource.master_id) ON specialty.speciality_id = master.speciality_id) INNER JOIN service ON specialty.speciality_id = service.speciality_id) INNER JOIN speciality_resource ON specialty.speciality_id = speciality_resource.speciality_id " +
            "WHERE (((service.service_id)=?) AND ((master_resource.locale)=?) AND ((speciality_resource.locale)=?));";

    public static List<Master> getAllMasters(String locale){
        List<Master> masters = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MASTERS);
            preparedStatement.setString(1,locale);
            preparedStatement.setString(2,locale);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Master master = new Master();
                master.setId(rs.getInt("master_id"));
                master.setFirstname(rs.getString("firstname"));
                master.setLastname(rs.getString("lastname"));
                master.setPhone_number(rs.getString("phone_number"));
                master.setMaster_rate(rs.getInt("master_rate"));
                master.setSpeciality_name(rs.getString("speciality_name"));
                master.setUser_id(rs.getInt("user_id"));
                masters.add(master);
            }
            rs.close();
            preparedStatement.close();

        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return masters;
    }

    public static List<Master> getMastersByServicesId(int id,String locale){
        List<Master> masters = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTERS_BY_SERVICES_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,locale);
            preparedStatement.setString(3,locale);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Master master = new Master();
                master.setId(rs.getInt("master_id"));
                master.setFirstname(rs.getString("firstname"));
                master.setLastname(rs.getString("lastname"));
                master.setPhone_number(rs.getString("phone_number"));
                master.setMaster_rate(rs.getInt("master_rate"));
                master.setSpeciality_name(rs.getString("speciality_name"));
                master.setUser_id(rs.getInt("user_id"));
                masters.add(master);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return masters;
    }

    /*public static List<String> getMastersScheduleDatesById(int id){
        List<String> schedules = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTER_SCHEDULE_DATES_BY_MASTER_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                schedules.add(rs.getString("date"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return schedules;
    }*/


    /*public static List<String> getMasterScheduleTimesById(int id) {
        List<String> times = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTER_SCHEDULE_TIMES_BY_MASTER_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                times.add(rs.getString("time"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return times;
    }*/
    public static List<Schedule> getMasterScheduleByMasterId(int id){
        List<Schedule> schedules = new ArrayList<>();

        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTER_SCHEDULE_BY_MASTER_ID);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setTime(rs.getString("time"));
                schedule.setDate(rs.getString("date"));
                schedule.setRecordId(rs.getInt("record_id"));
                schedules.add(schedule);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            e.printStackTrace();
        }
        finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return schedules;
    }
    /*public static List<Integer> getRecordDetailsIdsByMasterIdAndDate(int id,String date) {
        List<Integer> records = new ArrayList<>();
        Connection connection =null;
        try {
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SCHEDULE_RECORD_ID_BY_MASTER_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,date);
            ResultSet rs = preparedStatement.executeQuery();
            int countTimes = 0;
            while (rs.next()) {
                records.add(rs.getInt("record_id"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return records;
    }*/
}
