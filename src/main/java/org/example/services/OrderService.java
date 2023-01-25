package org.example.services;

import org.example.entity.Order;
import org.example.repository.OrderRepository;

public class OrderService extends ServiceImpl<OrderRepository, Order,Long>{
    public OrderService(OrderRepository orderRepository) {
        super(orderRepository);
    }
}
