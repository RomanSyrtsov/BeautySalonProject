package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.model.Master;
import ua.kharkiv.rsyrtsov.db.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDao {
    private static final String SELECT_ALL_MASTERS = "SELECT master.master_id,master.firstname, master.lastname, master.phone_number, master.master_rate, specialty.speciality_name, user.user_id " +
            "FROM beautysalon.user INNER JOIN (beautysalon.specialty INNER JOIN beautysalon.master ON beautysalon.specialty.speciality_id = master.speciality_id) ON user.user_id = master.user_id;";
    private static final String SELECT_MASTERS_BY_SERVICES_ID = "SELECT master.master_id, master.firstname, master.lastname, master.phone_number, specialty.speciality_name, master.master_rate, master.user_id " +
            "FROM (specialty INNER JOIN master ON specialty.speciality_id = master.speciality_id) INNER JOIN service ON specialty.speciality_id = service.speciality_id " +
            "WHERE (((service.service_id)=?));";

    public static List<Master> getAllMasters(){
        List<Master> masters = new ArrayList<>();

        try (Connection connection = DBManager.getConnectionWithDriverManager()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MASTERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Master master = new Master();
                master.setId(rs.getLong("master_id"));
                master.setFirstname(rs.getString("firstname"));
                master.setLastname(rs.getString("lastname"));
                master.setPhone_number(rs.getString("phone_number"));
                master.setMaster_rate(rs.getInt("master_rate"));
                master.setSpeciality_name(rs.getString("speciality_name"));
                master.setUser_id(rs.getInt("user_id"));
                masters.add(master);
            }
            rs.close();
            DBManager.commitAndClose(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return masters;
    }

    public static List<Master> getMastersByServicesId(int id){
        List<Master> masters = new ArrayList<>();
        try (Connection connection = DBManager.getConnectionWithDriverManager()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MASTERS_BY_SERVICES_ID);
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Master master = new Master();
                master.setId(rs.getLong("master_id"));
                master.setFirstname(rs.getString("firstname"));
                master.setLastname(rs.getString("lastname"));
                master.setPhone_number(rs.getString("phone_number"));
                master.setMaster_rate(rs.getInt("master_rate"));
                master.setSpeciality_name(rs.getString("speciality_name"));
                master.setUser_id(rs.getInt("user_id"));
                masters.add(master);
            }
            rs.close();
            DBManager.commitAndClose(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return masters;
    }

}
