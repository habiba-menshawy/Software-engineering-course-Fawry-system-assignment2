package com.FawrySystem.FawrySystemService.formsPackage.formsHandlers;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.paymentPackage.PaymentHandler;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.CreditCard;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

import java.util.HashMap;

public abstract class FormsHandler {
    protected HashMap<String, String> extraInformation = new HashMap<>();

    Float amount;
    String paymentType;
    CreditCard creditCard = new CreditCard();


    protected abstract void extractInformation();

    protected abstract void setPassedForm(Form form);

    public Float getAmount() {
        return amount;
    }

    public HashMap<String, String> getExtraInformation() {
        return extraInformation;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    protected boolean choosePayment() {
        PaymentHandler paymentHandler = new PaymentHandler();
        return paymentHandler.choosePaymentStrategy(paymentType, amount, getCreditCard());
    }

    protected void createTransaction(String spname, Customer currentCustomer, Float amount, Float appliedDiscount) {
        float payAmount = amount - (amount * appliedDiscount);
        TransactionRepository transactionRepository = new TransactionRepository();
        int lastID = TransactionRepository.getTransactions().size() + 1;
        Transaction newTransaction = new Transaction(spname, currentCustomer, payAmount, lastID, getExtraInformation());
        transactionRepository.addTransaction(newTransaction);
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
