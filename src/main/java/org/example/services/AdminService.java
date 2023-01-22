package org.example.services;

import org.example.entity.Admin;
import org.example.repository.AdminRepository;

public class AdminService {
    public static boolean login(Admin admin) throws Exception {
        if (AdminRepository.login(admin)){
            System.out.println("Admin Logged in Successfully");
            return true;
        }
        return false;
    }
    
}
