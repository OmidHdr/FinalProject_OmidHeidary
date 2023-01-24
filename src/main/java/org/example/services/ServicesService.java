package org.example.services;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Services;
import org.example.entity.User;
import org.example.repository.BaseRepository;
import org.example.repository.RepositoryImpl;
import org.example.repository.ServicesRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServicesService extends ServiceImpl<ServicesRepository,Services,Long>{


    public ServicesService(ServicesRepository servicesRepository) {
        super(servicesRepository);
    }



}
