package com.FawrySystem.FawrySystemService.serviceProviderPackage;


import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.FormsHandler;
import com.FawrySystem.FawrySystemService.formsPackage.formsHandlers.LandlineFormsHandler;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;

public class LandlineSP extends ServiceProvider {
    private static Float landlineDiscount = 0.0F;
    private String name;
    Form form = new LandlineForm();
    FormsHandler handler = new LandlineFormsHandler();

    public LandlineSP() {
        this.name = "Landline Service Provider";
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
        landlineDiscount = landlineDiscount + amount;
    }

    @Override
    public Float getDiscount() {
        return landlineDiscount;
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
