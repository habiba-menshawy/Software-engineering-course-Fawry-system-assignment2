package com.FawrySystem.FawrySystemService.serviceProviderPackage.serviceProvidersControllers;


import com.FawrySystem.FawrySystemService.formsPackage.forms.*;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.FawrySystem.FawrySystemService.serviceProviderPackage.serviceProvidersBSL.*;

@Component
@RestController
@RequestMapping("vodafone")
public class VodafoneSPController {


    PhoneServiceProvidersBSL phoneServiceProvidersBSL = new PhoneServiceProvidersBSL();

    @PostMapping(value = "internetPayment", consumes = {"application/json"})
    public ResponseEntity<Object> VodafoneInternetPayment(@RequestBody PhoneForm form){

        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>("Login as a customer", HttpStatus.UNAUTHORIZED);

        Transaction createdTransaction = phoneServiceProvidersBSL.invokePhoneServiceProvider(form, 1, "vodafone");
        if (createdTransaction != null) {
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "mobileRecharge", consumes = {"application/json"})
    public ResponseEntity<Object> VodafoneMobileRecharge(@RequestBody PhoneForm form){

        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>("Login as a customer", HttpStatus.UNAUTHORIZED);


        Transaction createdTransaction = phoneServiceProvidersBSL.invokePhoneServiceProvider(form, 2, "vodafone");
        if (createdTransaction != null) {
            return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
