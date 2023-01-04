package com.FawrySystem.FawrySystemService.serviceProviderPackage;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;

public abstract class ServiceProvider {
    protected String name;

    protected abstract boolean sendTransactionInformation();
    public abstract boolean passForm(Form form);

    public abstract void updateDiscount(Float d);

    public abstract Float getDiscount();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
