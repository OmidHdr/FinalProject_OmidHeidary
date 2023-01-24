package org.example.services;

import org.example.entity.Expert;
import org.example.entity.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserService extends ServiceImpl<UserRepository,User,Long> {
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public static boolean login(User user) throws Exception {
        if (UserRepository.login(user)){
            System.out.println("User Logged in Successfully");
            return true;
        }
        return false;
    }

    //section find Inactive Users
    public static List<Expert> findInactiveUsers() throws Exception {
        return UserRepository.findInactiveExperts();
    }

}
