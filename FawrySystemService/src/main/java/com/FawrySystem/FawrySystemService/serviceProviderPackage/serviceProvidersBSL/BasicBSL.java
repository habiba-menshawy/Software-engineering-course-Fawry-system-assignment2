package com.FawrySystem.FawrySystemService.serviceProviderPackage.serviceProvidersBSL;

import com.FawrySystem.FawrySystemService.formsPackage.forms.DonationsForm;
import com.FawrySystem.FawrySystemService.formsPackage.forms.Form;
import com.FawrySystem.FawrySystemService.formsPackage.forms.LandlineForm;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.DonationSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.LandlineSP;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.ServiceProvidersRepository;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;

import java.util.Vector;

public class BasicBSL {

    public Transaction invokeDonationsSP(Form form) {
        DonationSP donationSP = new DonationSP();
        boolean status = donationSP.passForm(form);
        if (status) {
            TransactionRepository tp = new TransactionRepository();
            int lastID = tp.getTransactions().size();
            return tp.findTransaction(lastID);
        }
        return null;
    }

    public Transaction invokeLandlineSP(Form form) {
        LandlineSP landlineSP = new LandlineSP();
        boolean status = landlineSP.passForm(form);
        if (status) {
            TransactionRepository tp = new TransactionRepository();
            int lastID = tp.getTransactions().size();
            return tp.findTransaction(lastID);
        }
        return null;
    }

    public Vector<String> searchSP(String spname) {
        ServiceProvidersRepository repository = new ServiceProvidersRepository();
        Vector<String> available = repository.getAvailableServiceProviders();
        Vector<String> result = new Vector<>();
        for (String name : available) {
            if (name.toLowerCase().contains(spname.toLowerCase())) {
                result.add(name);
            }
        }
        return result;
    }


}
