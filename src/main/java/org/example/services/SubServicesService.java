package org.example.services;

import org.example.entity.SubServices;
import org.example.repository.SubServiceRepository;

import java.util.List;

public class SubServicesService extends ServiceImpl<SubServiceRepository, SubServices,Long>{
    public SubServicesService(SubServiceRepository subServiceRepository) {
        super(subServiceRepository);
    }

    public static List<SubServices> selectSubService(Long id){
        return SubServiceRepository.findServices(id);
    }
}
