package com.FawrySystem.FawrySystemService.serviceProviderPackage.MobileRechargeSPs;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.FormsHandler;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.PhoneServicesHandler;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvider;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;


public class MobileRechargeSP extends ServiceProvider {
    private static Float mobileDiscount = 0.0F;

    String name;
    Form form = new PhoneForm();
    FormsHandler handler = new PhoneServicesHandler();

    public MobileRechargeSP() {
        this.name = "Mobile Service Provider";
    }

    @Override
    protected boolean sendTransactionInformation() {
        return handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
    }

    @Override
    public boolean passForm(Form form) {
        this.form = form;
        return sendTransactionInformation();
    }


    @Override
    public void updateDiscount(Float amount) {
        mobileDiscount = mobileDiscount + amount;
    }

    @Override
    public Float getDiscount() {
        return mobileDiscount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
