package org.example.services;

import org.example.entity.Expert;
import org.example.entity.User;
import org.example.repository.UserRepository;

import java.util.List;

public class UserService extends ServiceImpl<UserRepository,User,Long> {
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public static User login(User user) throws Exception {
        UserRepository userRepository = new UserRepository();
        final User login = userRepository.login(user);
        if (login != null) {
            System.out.println("User Logged in Successfully");
            return login;
        }
        return null;
    }

    //section find Inactive Users
    public static List<Expert> findInactiveUsers() throws Exception {
        return UserRepository.findInactiveExperts();
    }

}
