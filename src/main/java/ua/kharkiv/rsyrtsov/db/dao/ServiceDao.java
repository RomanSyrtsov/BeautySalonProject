package ua.kharkiv.rsyrtsov.db.dao;

import ua.kharkiv.rsyrtsov.db.model.Service;

import java.util.List;

public interface ServiceDao {

    List<Service> getAllServices(String locale);

    List<Service> getServiceByMasterId(int id,String locale);

}
