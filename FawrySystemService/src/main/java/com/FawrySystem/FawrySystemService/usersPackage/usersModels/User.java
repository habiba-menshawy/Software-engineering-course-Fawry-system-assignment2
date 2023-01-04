package com.FawrySystem.FawrySystemService.usersPackage.usersModels;

public abstract class User {
    protected String username;
    protected String email;
    protected String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String customerName) {
        this.username = customerName;
    }

    public String getUsername() {
        return username;
    }
}
