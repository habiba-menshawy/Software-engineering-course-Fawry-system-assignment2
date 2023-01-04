package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

public class PhoneServicesHandler extends FormsHandler {
    PhoneForm passedForm;


    protected void setPassedForm(Form passedForm) {
        this.passedForm = (PhoneForm) passedForm;
    }

    protected void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
        creditCard = passedForm.getCreditCard();
        extraInformation.put("phone number",passedForm.getPhoneNumber());
    }

    public boolean handlePaymentRequest(Form form, String spname, Customer currentCustomer, Float appliedDiscount) {
        setPassedForm(form);
        extractInformation();
        if (choosePayment()) {
            createTransaction(spname, currentCustomer, amount, appliedDiscount);
            return true;
        } return false;
    }


}
