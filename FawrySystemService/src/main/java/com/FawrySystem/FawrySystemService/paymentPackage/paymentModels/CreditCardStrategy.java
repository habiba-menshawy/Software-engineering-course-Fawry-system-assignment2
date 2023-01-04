package com.FawrySystem.FawrySystemService.paymentPackage.paymentModels;

import com.FawrySystem.FawrySystemService.usersPackage.usersModels.CreditCard;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

public class CreditCardStrategy implements PaymentStrategy {
    CreditCard creditCard;

    public CreditCardStrategy(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public boolean pay(float amount, Customer customer) {
        return (creditCard.getCardNumber().length() == 12) && (creditCard.getCvv().length() == 4)
                && (creditCard.getDateOfExpiry() <= 2030);
    }


}
