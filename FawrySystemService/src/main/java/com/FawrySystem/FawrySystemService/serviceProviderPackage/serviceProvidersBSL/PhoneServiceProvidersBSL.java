package com.FawrySystem.FawrySystemService.serviceProviderPackage.serviceProvidersBSL;

import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.PhoneForm;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.InternetPaymentSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.OrangeInternetPayment;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.InternetPaymentSPs.VodafoneInternetPayment;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.MobileRechargeSPs.MobileRechargeSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.MobileRechargeSPs.OrangeMobileRecharge;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.MobileRechargeSPs.VodafoneMobileRecharge;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;

public class PhoneServiceProvidersBSL {


    public InternetPaymentSP chooseInternetChild(String name) {
        if (name.toLowerCase().contains("vodafone"))
            return new VodafoneInternetPayment();

        if (name.toLowerCase().contains("orange"))
            return new OrangeInternetPayment();

        return null;
    }

    public MobileRechargeSP chooseMobileChild(String name) {
        if (name.toLowerCase().contains("vodafone"))
            return new VodafoneMobileRecharge();

        if (name.toLowerCase().contains("orange"))
            return new OrangeMobileRecharge();

        return null;    }

    public Transaction invokePhoneServiceProvider(Form passedForm, int spType, String spname) {
        if (spType == 1) {
            InternetPaymentSP internetPaymentSP = chooseInternetChild(spname);
            if (internetPaymentSP != null) {
                boolean status = internetPaymentSP.passForm(passedForm);
                if (status) {
                    TransactionRepository tp = new TransactionRepository();
                    int lastID = tp.getTransactions().size();
                    return tp.findTransaction(lastID);
                }
            }
        }

        if (spType == 2) {
            MobileRechargeSP mobileRechargeSP = chooseMobileChild(spname);
            if (mobileRechargeSP != null) {
                boolean status = mobileRechargeSP.passForm(passedForm);
                if (status) {
                    TransactionRepository tp = new TransactionRepository();
                    int lastID = tp.getTransactions().size();
                    return tp.findTransaction(lastID);
                }
            }
        }
        return null;
    }

}
