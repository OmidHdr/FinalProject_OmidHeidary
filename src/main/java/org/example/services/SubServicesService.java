package org.example.services;

import org.example.entity.SubServices;
import org.example.repository.BaseRepository;
import org.example.repository.SubServiceRepository;

public class SubServicesService extends ServiceImpl<SubServiceRepository, SubServices,Long>{
    public SubServicesService(SubServiceRepository subServiceRepository) {
        super(subServiceRepository);
    }
}
