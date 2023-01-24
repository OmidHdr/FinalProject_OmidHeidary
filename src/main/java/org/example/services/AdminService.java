package org.example.services;

import org.example.entity.User;
import org.example.repository.AdminRepository;

public class AdminService extends ServiceImpl<AdminRepository,User,Long>{
    public AdminService(AdminRepository adminRepository) {
        super(adminRepository);
    }


    public static boolean login(User user) throws Exception {
        if (AdminRepository.login(user)){
            System.out.println("Admin Logged in Successfully");
            return true;
        }
        return false;
    }
    
}
