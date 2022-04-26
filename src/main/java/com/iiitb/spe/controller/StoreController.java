package com.iiitb.spe.controller;

import com.iiitb.spe.model.entities.ItemEntity;
import com.iiitb.spe.model.entities.OrderEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.OrderRepository;
import com.iiitb.spe.services.ItemEntityService;
import com.iiitb.spe.services.OrderEntityService;
import com.iiitb.spe.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/store")
@CrossOrigin("*")
public class StoreController {

    @Autowired
    private OrderEntityService orderEntityService;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private ItemEntityService itemEntityService;



    @RequestMapping(value = "/getAllOrders")
    public ResponseEntity<?> getAllOrders(){
        UserEntity user = userEntityService.getDetails();
        List<OrderEntity> orders = orderEntityService.getAllOrder(user.getId());
        return ResponseEntity.ok(orders);
    }

    @RequestMapping(value = "/getAllItems")
    public ResponseEntity<?> getAllItems() {
        List<ItemEntity> items = itemEntityService.getAllItems();
        System.out.println(items.size());
        return ResponseEntity.ok(items);
    }

    @RequestMapping(value="/placeAnOrder",method = RequestMethod.POST)
    public ResponseEntity<?> placeAnOrder(@RequestBody Map<String,Object> payload, @RequestHeader Map<String,String> headers) throws Exception{

        UserEntity user = userEntityService.getDetails();
        String coinUpdate = userEntityService.updateCoins(payload,user,false);
        String res = orderEntityService.placeAnOrder(payload,user);
        Map<String,String> m = new HashMap<>();
        m.put("result",res);
        return ResponseEntity.ok(m);

    }

}
