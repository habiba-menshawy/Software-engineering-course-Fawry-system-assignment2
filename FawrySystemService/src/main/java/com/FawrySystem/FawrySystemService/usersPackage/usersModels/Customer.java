package com.FawrySystem.FawrySystemService.usersPackage.usersModels;

public class Customer extends User {
    private float wallet;

    public Customer() {
    }

    public Customer(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
        this.setWallet(0.0F);

    }


    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public float getWallet() {
        return wallet;
    }
}
