package ua.kharkiv.rsyrtsov.service;

import ua.kharkiv.rsyrtsov.db.model.Service;

import java.util.List;

public interface ServiceService {

    List<Service> getAllServices(String locale);

    List<Service> getServiceByMasterId(int id,String locale);

}
