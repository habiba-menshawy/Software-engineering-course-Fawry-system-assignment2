package com.FawrySystem.FawrySystemService.serviceProviderPackage;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.DonationFormsHandler;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.FormsHandler;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;

public class DonationSP extends ServiceProvider {
    private static Float donationDiscount = 0.0F;
    String name;
    Form form = new DonationsForm();
    FormsHandler handler = new DonationFormsHandler();

    public DonationSP() {
        this.name = "Donations Service Provider";
    }

    @Override
    public boolean passForm(Form form) {
        this.form = form;
        return sendTransactionInformation();
    }

    @Override
    protected boolean sendTransactionInformation() {
        return handler.handlePaymentRequest(form, name, CustomerController.currentCustomer, getDiscount());
    }

    @Override
    public void updateDiscount(Float amount) {
        donationDiscount = donationDiscount + amount;
    }

    @Override
    public Float getDiscount() {
        return donationDiscount;
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
