package org.example.repository;

import org.example.entity.Services;
import org.example.entity.User;

public class ServicesRepository extends RepositoryImpl<Services, Long>{

    public static boolean login(User user) throws Exception {
        if (UserRepository.login(user)!= null){
            System.out.println("User Logged in Successfully");
            return true;
        }
        return false;
    }

}
