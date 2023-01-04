package com.FawrySystem.FawrySystemService.formsPackage.forms;

import com.FawrySystem.FawrySystemService.usersPackage.usersModels.CreditCard;

public abstract class Form {
    protected Float pay_amount;
    protected String paymentType;

    protected CreditCard creditCard;

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Float getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(Float pay_amount) {
        this.pay_amount = pay_amount;
    }
}
