package com.iiitb.spe.controller;


import com.iiitb.spe.model.entities.EndorsementEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.services.EndorsementEntityService;
import com.iiitb.spe.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private EndorsementEntityService endorsementEntityService;

    @RequestMapping("/getAllEndorsements")
    public ResponseEntity<?> getAllEndorsements(){
        System.out.println("In get all Endorsements api");
        UserEntity user = userEntityService.getDetails();
        List<EndorsementEntity> endorsements = endorsementEntityService.getAllEndorsements(user.getId());
        return ResponseEntity.ok(endorsements);
    }
    @RequestMapping("/getAllUserEndorsements")
    public ResponseEntity<?> getAllUserEndorsements(){
        System.out.println("In get all UserEndorsements api");
        UserEntity user = userEntityService.getDetails();
        List<EndorsementEntity> endorsements = endorsementEntityService.getAllUserEndorsements(user.getId());
        return ResponseEntity.ok(endorsements);
    }

    @RequestMapping(value="/addEndorsement", method = RequestMethod.POST)
    public ResponseEntity<?> addPatient(@RequestBody Map<String,Object> payload, @RequestHeader Map<String,String> headers) throws Exception{
        System.out.println("in add patient api");
        UserEntity user = userEntityService.getDetails();

        int coins = Integer.valueOf((String) payload.get("coins"));
        String update = userEntityService.updateCoins(user.getCoinsBalance()-coins,user.getId());

        String res = endorsementEntityService.addEndorsement(payload,headers.get("authorization"),user);
        return  ResponseEntity.ok(res);
    }
}
