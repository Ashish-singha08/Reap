package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.OrderEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.ItemRepository;
import com.iiitb.spe.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderEntityService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<OrderEntity> getAllOrder(long id){
        List<OrderEntity> orders = orderRepository.findByUserId(id);
        return orders;
    }

    public String placeAnOrder(Map<String,Object> payload, UserEntity user){
        try{
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUserId (user.getId());
            orderEntity.setItemId(Long.valueOf((String) payload.get("itemId")));
            Date date = new Date();
            orderEntity.setOrderedOn(new Timestamp(date.getTime()));
            orderEntity.setItemByItemId(itemRepository.findItemById(orderEntity.getItemId()));
            OrderEntity order = orderRepository.save(orderEntity);
            System.out.println(order.getId());
           return "Order ADDED";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
