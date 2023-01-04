package com.FawrySystem.FawrySystemService.transactionsPackage.transactionsBSL;

import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.transactionsPackage.TransactionRepository;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;

import java.util.HashMap;

public class RefundBSL {
    private final TransactionRepository transactionRepository = new TransactionRepository();

    public int requestRefund(Integer TID) {
        Transaction toBeRefunded = transactionRepository.findTransaction(TID);
        if (toBeRefunded != null) {
            if (CustomerController.currentCustomer == toBeRefunded.getCustomer()) {
                // set refund attribute with true to indicate the refund request
                toBeRefunded.setRefund(true);
                // call request refund function to add the transaction to the list of refund requests
                transactionRepository.requestRefund(toBeRefunded);
                return 3;
            } else return 2;
        } else return 1;
    }

    public Transaction returnRefund (Integer TID) {
        return transactionRepository.findTransaction(TID);
    }

    public HashMap<Integer, Transaction> getRefunds() {
        return TransactionRepository.getRefunds();
    }

    public boolean acceptRefundRequest(Integer TID) {
        Transaction acceptedRefund = transactionRepository.findRefund(TID);
        if (acceptedRefund != null) {
            // get the pay amount of the transaction
            float amount = acceptedRefund.getPay_amount();
            // return pay amount to the customer wallet
            acceptedRefund.getCustomer().setWallet(acceptedRefund.getCustomer().getWallet() + amount);
            // set refund attribute into false to indicate the end of the request
            acceptedRefund.setRefund(false);
            // remove request from list of refunds
            transactionRepository.removeRefundRequest(TID);
            return true;
        }
        return false;
    }


    public boolean refuseRefundRequest(int id) {
        // find and get the transaction from the map of transactions
        Transaction refusedRefund = transactionRepository.findRefund(id);
        if (refusedRefund != null) {
            // set refund attribute into false to indicate the end of the request
            refusedRefund.setRefund(false);
            // remove request from list of refunds
            transactionRepository.removeRefundRequest(id);
            return true;
        }
        return false;
    }


}
