package com.FawrySystem.FawrySystemService.transactionsPackage.transactionControllers;

import com.FawrySystem.FawrySystemService.transactionsPackage.transactionsBSL.RefundBSL;
import com.FawrySystem.FawrySystemService.transactionsPackage.Transaction;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.AdminController;
import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.CustomerController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// controller to handle all refund functionalities
@Component
@RestController
@RequestMapping("/refund")
public class RefundController {
    private final RefundBSL refundBSL = new RefundBSL();


    //http://localhost:8080/refund/listRefunds
    @GetMapping("/listRefunds")
    private ResponseEntity<Object> listRefunds() {

        if (AdminController.currentAdmin == null)
            return new ResponseEntity<>("login as an admin", HttpStatus.UNAUTHORIZED);

        HashMap<Integer, Transaction> refundsList = refundBSL.getRefunds();
        return new ResponseEntity<>(refundsList, HttpStatus.OK);
    }


    // http://localhost:8080/refund/requestRefund
    @PostMapping(value = "/requestRefund", consumes = {"application/json"})
    // request refund for the specified transaction
    public ResponseEntity<Object> requestRefund(@RequestBody Map<String, Integer> transactionID) {

        ResponseEntity<Object> response = null;
        if (CustomerController.currentCustomer == null)
            response = new ResponseEntity<>("login as a customer", HttpStatus.UNAUTHORIZED);
        else {
            int TID = transactionID.get("transaction ID");
            switch (refundBSL.requestRefund(TID)) {
                case 1:
                    response = new ResponseEntity<>("Transaction not found", HttpStatus.BAD_REQUEST);
                    break;
                case 2:
                    response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
                    break;
                case 3:
                    response = new ResponseEntity<>(refundBSL.returnRefund(TID),HttpStatus.OK);
                    break;
            }
        }
        return response;
    }

    //http://localhost:8080/refund/acceptRefund
    @PostMapping(value = "/acceptRefund", consumes = {"application/json"})
    public ResponseEntity<Object> acceptRefund(@RequestBody Map<String, Integer> transactionID) {

        ResponseEntity<Object> response = null;

        if (AdminController.currentAdmin == null)
            response = new ResponseEntity<>("login as an admin", HttpStatus.UNAUTHORIZED);
        else {
            int TID = transactionID.get("transaction ID");
            if (refundBSL.acceptRefundRequest(TID))
                response = new ResponseEntity<>(HttpStatus.OK);
            else
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        return response;
    }

    //http://localhost:8080/refund/refuseRefund
    @PostMapping(value = "/refuseRefund", consumes = {"application/json"})
    public ResponseEntity<Object> refuseRefund(@RequestBody Map<String, Integer> transactionID) {

        ResponseEntity<Object> response = null;

        if (AdminController.currentAdmin == null)
            response = new ResponseEntity<>("login as an admin", HttpStatus.UNAUTHORIZED);
        else {
            int TID = transactionID.get("transaction ID");
            if (refundBSL.refuseRefundRequest(TID))
                response = new ResponseEntity<>(HttpStatus.OK);
            else
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }


}
