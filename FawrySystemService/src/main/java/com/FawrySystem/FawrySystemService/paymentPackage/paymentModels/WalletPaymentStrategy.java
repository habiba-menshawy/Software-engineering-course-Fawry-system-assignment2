package com.FawrySystem.FawrySystemService.paymentPackage.paymentModels;

import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

public class WalletPaymentStrategy implements PaymentStrategy {

    public boolean pay(float amount, Customer customer) {
        float currentWallet = customer.getWallet();
        if (currentWallet >= amount) {
            customer.setWallet(currentWallet - amount);
            return true;
        } else return false;
    }
}
