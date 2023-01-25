package org.example.services;

import org.example.entity.User;
import org.example.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminService extends ServiceImpl<AdminRepository,User,Long>{
    public static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    public AdminService(AdminRepository adminRepository) {
        super(adminRepository);
    }


    public static boolean login(User user) throws Exception {
        if (AdminRepository.login(user)){
            System.out.println("Admin Logged in Successfully");
            logger.info("Admin {} Logged in successfully ",user.getUsername());
            return true;
        }
        return false;
    }
    
}
