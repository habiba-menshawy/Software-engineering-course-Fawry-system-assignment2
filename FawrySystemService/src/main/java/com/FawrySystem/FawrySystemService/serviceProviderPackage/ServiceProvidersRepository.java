package com.FawrySystem.FawrySystemService.serviceProviderPackage;

import java.util.Vector;

public class ServiceProvidersRepository {
    Vector<String> availableServiceProviders = new Vector<>();
    public ServiceProvidersRepository() {
        availableServiceProviders.add("Vodafone Internet Payment Service Provider");
        availableServiceProviders.add("Orange Internet Payment Service Provider");
        availableServiceProviders.add("Vodafone Mobile Recharge Service Provider");
        availableServiceProviders.add("Orange Mobile Recharge Service Provider");
        availableServiceProviders.add("Donations Service Provider");
        availableServiceProviders.add("Landline Service Provider");
    }

    public Vector<String> getAvailableServiceProviders() {
        return availableServiceProviders;
    }
}
