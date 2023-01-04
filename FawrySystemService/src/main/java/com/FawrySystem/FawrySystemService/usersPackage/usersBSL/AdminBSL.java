package com.FawrySystem.FawrySystemService.usersPackage.usersBSL;

import com.FawrySystem.FawrySystemService.usersPackage.usersControllers.AdminController;
import com.FawrySystem.FawrySystemService.usersPackage.usersModels.Admin;
import com.FawrySystem.FawrySystemService.usersPackage.usersRepository.AdminRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AdminBSL {
    private static final AdminRepository adminRepository = new AdminRepository();

    static public Admin getAdminByEmail(String email) {
        return AdminRepository.getAdminByEmail(email);
    }

    public static boolean addAdmin(Admin admin) {
        return AdminRepository.addAdmin(admin);
    }

    public int loginByEmail(Admin admin) {
        String email = admin.getEmail();
        String password = admin.getPassword();
        Admin temp = AdminBSL.getAdminByEmail(email);
        if (temp != null) {
            if (Objects.equals(password, temp.getPassword())) {
                AdminController.currentAdmin = temp;
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

}
