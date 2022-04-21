package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.OrderEntity;
import com.iiitb.spe.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEntityService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAllOrder(long id){
        List<OrderEntity> orders = orderRepository.findById(id);
        return orders;
    }
}
