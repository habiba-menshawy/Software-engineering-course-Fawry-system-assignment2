package com.FawrySystem.FawrySystemService.usersPackage.usersControllers;

import com.FawrySystem.FawrySystemService.usersPackage.usersBSL.AdminBSL;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    public static Admin currentAdmin = null;


    //http://localhost:8080/admin/signup
    @PostMapping(value = "/signup", consumes = {"application/json"})
    public ResponseEntity<Object> signUpAdmin(@RequestBody Admin admin) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        if (email == null || password == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (AdminBSL.addAdmin(admin)) {
            return new ResponseEntity<>(admin, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Customer already exists", HttpStatus.BAD_REQUEST);
        }
    }


    //http://localhost:8080/admin/login
    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<Object> loginAdmin(@RequestBody Admin admin) {

        String email = admin.getEmail();
        String username = admin.getUsername();
        ResponseEntity<Object> response = null;

        if (currentAdmin == null) {
            AdminBSL adminBSL = new AdminBSL();
            if (username == null) {
                int result = adminBSL.loginByEmail(admin);
                switch (result) {
                    case 1 -> response = new ResponseEntity<>(HttpStatus.OK);
                    case 2 -> response = new ResponseEntity<>("Wrong Password", HttpStatus.UNAUTHORIZED);
                    case 3 -> response = new ResponseEntity<>("Customer doesn't exist", HttpStatus.UNAUTHORIZED);
                }
            } else if (email == null) response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            response = new ResponseEntity<>("Log out first", HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    //http://localhost:8080/admin/logout
    @GetMapping(value = "/logout")
    public ResponseEntity<Object> logoutAdmin() {
        ResponseEntity<Object> response = null;
        if (currentAdmin != null) {
            currentAdmin = null;
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}

