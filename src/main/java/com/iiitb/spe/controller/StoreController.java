package com.iiitb.spe.controller;

import com.iiitb.spe.model.entities.OrderEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.OrderRepository;
import com.iiitb.spe.services.OrderEntityService;
import com.iiitb.spe.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StoreController {

    @Autowired
    private OrderEntityService orderEntityService;

    @Autowired
    private UserEntityService userEntityService;

    @RequestMapping(value = "/getAllOrder")
    public ResponseEntity<?> getAllOrders(){
        UserEntity user = userEntityService.getDetails();
        List<OrderEntity> orders = orderEntityService.getAllOrder(user.getId());
        return ResponseEntity.ok(orders);
    }
}
