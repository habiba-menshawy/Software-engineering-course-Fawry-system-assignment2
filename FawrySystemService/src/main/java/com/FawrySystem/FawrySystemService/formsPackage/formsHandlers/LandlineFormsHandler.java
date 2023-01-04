package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

import java.util.HashMap;

public class LandlineFormsHandler extends FormsHandler {
    LandlineForm passedForm;

    protected void setPassedForm(Form passedForm) {
        this.passedForm = (LandlineForm) passedForm;
    }

    protected void extractInformation() {
        amount = passedForm.getPay_amount();
        paymentType = passedForm.getPaymentType();
        creditCard = passedForm.getCreditCard();
        extraInformation.put("Receipt type", passedForm.getType());
        extraInformation.put("landline number",passedForm.getLandlineNumber());
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
