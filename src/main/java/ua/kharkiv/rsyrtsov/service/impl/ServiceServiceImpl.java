package ua.kharkiv.rsyrtsov.service.impl;

import ua.kharkiv.rsyrtsov.db.dao.DAOProvider;
import ua.kharkiv.rsyrtsov.db.dao.ServiceDao;
import ua.kharkiv.rsyrtsov.db.model.Service;
import ua.kharkiv.rsyrtsov.service.ServiceService;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {
    @Override
    public List<Service> getAllServices(String locale) {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ServiceDao serviceDao = daoProvider.getServiceDao();
        List<Service> services;

        services = serviceDao.getAllServices(locale);

        return services;
    }

    @Override
    public List<Service> getServiceByMasterId(int id, String locale) {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ServiceDao serviceDao = daoProvider.getServiceDao();
        List<Service> services;

        services = serviceDao.getServiceByMasterId(id, locale);

        return services;
    }
}
