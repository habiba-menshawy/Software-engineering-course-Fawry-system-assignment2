package com.FawrySystem.FawrySystemService.usersPackage.usersControllers;

import com.FawrySystem.FawrySystemService.usersPackage.usersBSL.CustomerBSL;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    public static Customer currentCustomer = null;


    //http://localhost:8080/customer/signup
    @PostMapping(value = "/signup", consumes = {"application/json"})
    public ResponseEntity<Object> signUpCustomer(@RequestBody Customer customer) {
        String email = customer.getEmail();
        String username = customer.getUsername();
        String password = customer.getPassword();
        if (username == null || email == null || password == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        customer.setWallet(0);
        if (CustomerBSL.addCustomer(customer)) {
            return new ResponseEntity<>(customer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Customer already exists", HttpStatus.BAD_REQUEST);
        }
    }

    //http://localhost:8080/customer/login
    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<Object> loginCustomer(@RequestBody Customer customer) {

        String email = customer.getEmail();
        String username = customer.getUsername();
        ResponseEntity<Object> response = null;

        if (email == null && username == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (currentCustomer == null) {
            CustomerBSL customerBSL = new CustomerBSL();
            if (username != null && email != null) {

                int result = customerBSL.loginByEmail(customer);
                switch (result) {
                    case 1 -> response = new ResponseEntity<>(HttpStatus.OK);
                    case 2 -> response = new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
                    case 3 -> response = new ResponseEntity<>("Customer doesn't exist", HttpStatus.UNAUTHORIZED);
                }
            } else if (username == null) {
                int result = customerBSL.loginByEmail(customer);
                switch (result) {
                    case 1 -> response = new ResponseEntity<>(HttpStatus.OK);
                    case 2 -> response = new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
                    case 3 -> response = new ResponseEntity<>("Customer doesn't exist", HttpStatus.UNAUTHORIZED);
                }
            } else if (email == null) {
                int result = customerBSL.loginByUsername(customer);
                switch (result) {
                    case 1 -> response = new ResponseEntity<>(HttpStatus.OK);
                    case 2 -> response = new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
                    case 3 -> response = new ResponseEntity<>("Customer doesn't exist", HttpStatus.UNAUTHORIZED);
                }
            }
        } else {
            response = new ResponseEntity<>("Log out first", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    //http://localhost:8080/customer/logout
    @GetMapping(value = "/logout")
    public ResponseEntity<Object> logoutCustomer() {
        ResponseEntity<Object> response = null;
        if (currentCustomer != null) {
            currentCustomer = null;
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping(value = "checkAccount")
    public ResponseEntity<Object> checkAccountDetails() {
        if (currentCustomer == null)
            return new ResponseEntity<>("login first",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }


    }
