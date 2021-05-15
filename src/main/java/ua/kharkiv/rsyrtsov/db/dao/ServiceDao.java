package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {
    private static final String SELECT_ALL_SERVICES = "SELECT * FROM beautysalon.service;";
    private static final String SELECT_SERVICES_BY_MASTER_ID = "SELECT service.service_id, master.master_id, service.service_name, service.service_price, service.service_time, service.speciality_id " +
            "FROM (specialty INNER JOIN master ON specialty.speciality_id = master.speciality_id) INNER JOIN service ON specialty.speciality_id = service.speciality_id " +
            "WHERE (((master.master_id)=?));";

    public static List<Service> getAllServices(){
        List<Service> services = new ArrayList<>();

        try (Connection connection = DBManager.getConnectionWithDriverManager()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SERVICES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getLong("service_id"));
                service.setServiceName(rs.getString("service_name"));
                service.setServicePrice(rs.getDouble("service_price"));
                service.setServiceTime(rs.getInt("service_time"));
                service.setSpecialityId(rs.getInt("speciality_id"));
                services.add(service);
            }
            rs.close();
            DBManager.commitAndClose(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    public static List<Service> getServiceByMasterId(int id){
        List<Service> services = new ArrayList<>();
        try (Connection connection = DBManager.getConnectionWithDriverManager()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICES_BY_MASTER_ID);
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getLong("service_id"));
                service.setServiceName(rs.getString("service_name"));
                service.setServicePrice(rs.getDouble("service_price"));
                service.setServiceTime(rs.getInt("service_time"));
                service.setSpecialityId(rs.getInt("speciality_id"));
                services.add(service);
            }
            rs.close();
            DBManager.commitAndClose(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
}
