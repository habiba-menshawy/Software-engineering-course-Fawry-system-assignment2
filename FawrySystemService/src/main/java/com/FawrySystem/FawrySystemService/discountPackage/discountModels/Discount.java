package com.FawrySystem.FawrySystemService.discountPackage.discountModels;

import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;

import java.util.Vector;


// Discount class act as the subject class who the services are interested in its update
public abstract class Discount {

    // a vector holding the registered services (observers) to be notified when a discount is added
    protected Vector<ServiceProvider> services = new Vector<>();

    // adding services to be notified
    public void registerService(ServiceProvider service) {
        services.add(service);
    }

    // a function that will notify the registered services with the updated discount
    public abstract void setDiscount(Float amount);

    // another function that will notify the registered services by removing the discount
    public abstract void removeDiscount();

    public Vector<ServiceProvider> getServices() {
        return services;
    }
}
