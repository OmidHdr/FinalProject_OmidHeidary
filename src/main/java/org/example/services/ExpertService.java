package org.example.services;

import org.example.entity.Expert;
import org.example.entity.Order;
import org.example.repository.ExpertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExpertService extends ServiceImpl<ExpertRepository, Expert,Long> {

    public static final Logger logger = LoggerFactory.getLogger(ExpertService.class);

    public ExpertService(ExpertRepository expertRepository) {
        super(expertRepository);
    }

    public static List<Order> allJobs(Expert expert){
        return ExpertRepository.allJobs(expert);
    }

}
