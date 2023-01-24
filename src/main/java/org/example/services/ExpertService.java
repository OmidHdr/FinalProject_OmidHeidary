package org.example.services;

import org.example.entity.Expert;
import org.example.repository.ExpertRepository;

public class ExpertService extends ServiceImpl<ExpertRepository, Expert,Long> {
    public ExpertService(ExpertRepository expertRepository) {
        super(expertRepository);
    }
}
