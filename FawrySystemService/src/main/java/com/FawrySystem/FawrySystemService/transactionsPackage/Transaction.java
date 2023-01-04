package com.FawrySystem.FawrySystemService.transactionsPackage;

import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;

import java.util.HashMap;

public class Transaction {
    private String service_name;
    private Customer customer;
    private float pay_amount;
    private boolean refund = false;
    private int trans_ID;

    private HashMap<String , String > extraInfo = new HashMap<>();

    public Transaction(String s, Customer c, float a, int id, HashMap<String, String> extraInfo ) {
        this.setService_name(s);
        this.setCustomer(c);
        this.setPay_amount(a);
        this.setTrans_ID(id);
        this.setExtraInfo(extraInfo);
    }

    public HashMap<String, String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(HashMap<String, String> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void setRefund(boolean r) {

        this.refund = r;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPay_amount(float pay_amount) {
        this.pay_amount = pay_amount;
    }

    public boolean isRefund() {
        return refund;
    }

    public Customer getCustomer() {
        return customer;
    }

    public float getPay_amount() {
        return pay_amount;
    }


    public int getTrans_ID() {
        return trans_ID;
    }

    public void setTrans_ID(int trans_ID) {
        this.trans_ID = trans_ID;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

}
