package com.FawrySystem.FawrySystemService.paymentPackage;

import com.FawrySystem.FawrySystemService.paymentPackage.paymentModels.CODPaymentStrategy;
import com.FawrySystem.FawrySystemService.paymentPackage.paymentModels.CreditCardStrategy;
import com.FawrySystem.FawrySystemService.paymentPackage.paymentModels.PaymentStrategy;
import com.FawrySystem.FawrySystemService.paymentPackage.paymentModels.WalletPaymentStrategy;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.CreditCard;

public class PaymentHandler {

    String chosenPayment;
    Float amount;

    PaymentStrategy paymentStrategy;


    public boolean choosePaymentStrategy(String paymentType, Float amount, CreditCard creditCard) {
        if (paymentType.toLowerCase().contains("card")) {
            payCreditCard(creditCard);
        } else {
            chosenPayment = paymentType;
            normalPayment();
        }
        return paymentStrategy.pay(amount, CustomerController.currentCustomer);
    }


    private void normalPayment() {
        if (chosenPayment.toLowerCase().contains("wallet")) {
            paymentStrategy = new WalletPaymentStrategy();
        } else if (chosenPayment.toLowerCase().contains("cash")) {
            paymentStrategy = new CODPaymentStrategy();
        }
    }

    private void payCreditCard(CreditCard creditCard) {
        if (creditCard != null) {
            paymentStrategy = new CreditCardStrategy(creditCard);
        }
    }

}
