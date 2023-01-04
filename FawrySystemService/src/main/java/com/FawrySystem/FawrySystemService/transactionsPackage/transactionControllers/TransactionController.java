package com.FawrySystem.FawrySystemService.transactionsPackage.transactionControllers;

import com.FawrySystem.FawrySystemService.transactionsPackage.transactionsBSL.TransactionsBSL;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.AdminController;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.CreditCard;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;

@Component
@RestController
@RequestMapping("transaction")
public class TransactionController {
    TransactionsBSL transactionsBSL = new TransactionsBSL();


    //http://localhost:8080/transaction/addToWallet/{amount}
    @PostMapping(value = "/addToWallet/{amount}")
    public ResponseEntity<Object> addToWallet(@RequestBody CreditCard creditCard, @PathVariable Float amount) {

        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>("Login first as a customer", HttpStatus.UNAUTHORIZED);

        boolean flag = transactionsBSL.addToWallet(amount, creditCard);
        if (flag)
            return new ResponseEntity<>(CustomerController.currentCustomer, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/listTransactions")
    public ResponseEntity<Object> listTransactions () {
        if (AdminController.currentAdmin == null)
            return new ResponseEntity<>("login as an admin", HttpStatus.UNAUTHORIZED);

        HashMap<Integer, Transaction> transactions = transactionsBSL.getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping (value = "customerTransactions")
    public ResponseEntity<Object> getCustomerTransaction () {

        if (CustomerController.currentCustomer == null)
            return new ResponseEntity<>("Login first as a customer or an admin", HttpStatus.UNAUTHORIZED);

        Vector<Transaction> result = transactionsBSL.getCustomerTransactions();
        return new ResponseEntity<>(Objects.requireNonNullElseGet(result, () -> new Vector<Transaction>()), HttpStatus.OK);
    }


    @GetMapping (value = "/listWalletTransactions")
    public ResponseEntity<Object> listWalletTransactions () {
            if (AdminController.currentAdmin == null)
                return new ResponseEntity<>("login as an admin", HttpStatus.UNAUTHORIZED);

            HashMap<Integer, Transaction> transactions = transactionsBSL.getWalletTransactions();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        }



}
