package org.example.services;

import org.example.entity.Services;
import org.example.repository.BaseRepository;
import org.example.repository.ServicesRepository;

import java.util.List;

public class ServicesService extends ServiceImpl {
    public ServicesService(BaseRepository baseRepository) {
        super(baseRepository);
    }
    public static List<Services> showAllServices(){
        final ServicesRepository servicesRepository = new ServicesRepository();
        final List<Services> services = servicesRepository.allServices();
        return services;
    }
}
