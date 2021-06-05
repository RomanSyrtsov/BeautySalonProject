package ua.kharkiv.rsyrtsov.db.model;

import java.util.List;

public class ServiceContainer {
    private List<Service> services;

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public double getServicePriceById(Long id) {
        double price = 0;
        for (Service service : services) {
            if (service.getId() == id) {
                price = service.getServicePrice();
            }
        }
        return price;
    }

    public String getServiceNameById(Long id) {
        String serviceName = "";
        for (Service service : services) {
            if (service.getId() == id) {
                serviceName = service.getServiceName();
            }
        }
        return serviceName;
    }

}
