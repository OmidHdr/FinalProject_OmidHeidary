package org.example.services;

import org.example.entity.Expert;
import org.example.repository.ExpertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpertService extends ServiceImpl<ExpertRepository, Expert,Long> {
    public static final Logger logger = LoggerFactory.getLogger(ExpertService.class);
    public ExpertService(ExpertRepository expertRepository) {
        super(expertRepository);
    }
    public static Expert login(Expert user) throws Exception {
        ExpertRepository userRepository = new ExpertRepository();
        final Expert login = userRepository.login(user);
        if (login != null) {
            System.out.println("Expert Logged in Successfully");
            logger.info("Expert {} Logged in successfully ",user.getUsername());
            return login;
        }
        return null;
    }
}
