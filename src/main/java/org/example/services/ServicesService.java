package org.example.services;

import org.example.entity.Services;
import org.example.repository.ServicesRepository;

public class ServicesService extends ServiceImpl<ServicesRepository,Services,Long>{


    public ServicesService(ServicesRepository servicesRepository) {
        super(servicesRepository);
    }



}
