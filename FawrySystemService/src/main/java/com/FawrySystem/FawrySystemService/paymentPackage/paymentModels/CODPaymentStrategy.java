package com.FawrySystem.FawrySystemService.paymentPackage.paymentModels;

import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

public class CODPaymentStrategy implements PaymentStrategy {
    public boolean pay(float amount, Customer customer) {
        return true;
    }

}
