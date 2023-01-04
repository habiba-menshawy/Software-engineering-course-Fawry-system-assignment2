package com.FawrySystem.FawrySystemService.usersPackage.usersModels;

public class CreditCard {
    private String cardNumber;
    private String cvv;
    private int dateOfExpiry;


    public CreditCard() {
    }

    public CreditCard(String cardNumber, String cvv, int dateOfExpiry) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dateOfExpiry = dateOfExpiry;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(int dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
