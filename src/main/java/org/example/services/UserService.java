package org.example.services;

import org.example.entity.User;
import org.example.repository.RepositoryImpl;
import org.example.repository.UserRepository;

public class UserService extends RepositoryImpl<User, Integer> {
    public static boolean login(User user) throws Exception {
        if (UserRepository.login(user)){
            System.out.println("User Logged in Successfully");
            return true;
        }
        return false;
    }

}
