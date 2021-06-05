package ua.kharkiv.rsyrtsov.service;

import ua.kharkiv.rsyrtsov.service.impl.MasterServiceImpl;
import ua.kharkiv.rsyrtsov.service.impl.RecordServiceImpl;
import ua.kharkiv.rsyrtsov.service.impl.ServiceServiceImpl;
import ua.kharkiv.rsyrtsov.service.impl.UserServiceImlp;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImlp();

    private final MasterService masterService = new MasterServiceImpl();

    private final ServiceService serviceService = new ServiceServiceImpl();

    private final RecordService recordService = new RecordServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public MasterService getMasterService() {
        return masterService;
    }

    public ServiceService getServiceService(){
        return serviceService;
    }

    public RecordService getRecordService() {
        return recordService;
    }
}
