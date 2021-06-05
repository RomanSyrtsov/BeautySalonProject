package ua.kharkiv.rsyrtsov.db.dao.impl;

import ua.kharkiv.rsyrtsov.db.DBManager;
import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl implements ServiceDao {
    private static final String SELECT_ALL_SERVICES = "SELECT service.service_id, service_resource.service_name, service.service_price, service.service_time, service.speciality_id " +
            "FROM service INNER JOIN service_resource ON service.service_id = service_resource.service_id " +
            "WHERE (((service_resource.locale)=?));";
    private static final String SELECT_SERVICES_BY_MASTER_ID = "SELECT service.service_id, master.master_id, service_resource.service_name, service.service_price, service.service_time, service.speciality_id " +
            "FROM ((specialty INNER JOIN master ON specialty.speciality_id = master.speciality_id) INNER JOIN service ON specialty.speciality_id = service.speciality_id) INNER JOIN service_resource ON service.service_id = service_resource.service_id " +
            "WHERE (((master.master_id)=?) AND ((service_resource.locale)=?));";

    public List<Service> getAllServices(String locale){
        List<Service> services = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SERVICES);
            preparedStatement.setString(1,locale);
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
            preparedStatement.close();

        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            e.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return services;
    }

    public List<Service> getServiceByMasterId(int id,String locale){
        List<Service> services = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICES_BY_MASTER_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,locale);
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
            preparedStatement.close();
        } catch (SQLException e) {
            DBManager.getInstance().rollbackAndClose(connection);
            e.printStackTrace();
        }finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return services;
    }
}
