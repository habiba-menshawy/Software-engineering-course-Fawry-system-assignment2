package com.FawrySystem.FawrySystemService.paymentPackage.paymentModels;

import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

public interface PaymentStrategy {
    boolean pay(float amount, Customer customer);
}
