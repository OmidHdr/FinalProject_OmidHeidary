package org.example.services;

import org.example.entity.Expert;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserService extends ServiceImpl<UserRepository,User,Long> {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public static User login(User user) throws Exception {
        UserRepository userRepository = new UserRepository();
        final User login = userRepository.login(user);
        if (login != null) {
            System.out.println("User Logged in Successfully");
            logger.info("user {} Logged in successfully ",user.getUsername());
            return login;
        }
        return null;
    }

    //section find Inactive Users
    public static List<Expert> findInactiveUsers() throws Exception {
        return UserRepository.findInactiveExperts();
    }

}
