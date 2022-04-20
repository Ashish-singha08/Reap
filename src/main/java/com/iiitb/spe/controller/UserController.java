package com.iiitb.spe.controller;


import com.iiitb.spe.model.entities.EndorsementEntity;
import com.iiitb.spe.model.entities.MailEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.services.EndorsementEntityService;
import com.iiitb.spe.services.MailService;
import com.iiitb.spe.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private EndorsementEntityService endorsementEntityService;

    @Autowired
    private MailService mailService;

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
    public ResponseEntity<?> addEndorsement(@RequestBody Map<String,Object> payload, @RequestHeader Map<String,String> headers) throws Exception{
        System.out.println("in add Endorsement api");
        UserEntity user = userEntityService.getDetails();



        //Coins Updation
        int coins = Integer.valueOf((String) payload.get("coins"));
        String update = userEntityService.updateCoins(user.getCoinBalance()-coins,user.getId());

        //Endorsement Insertion
        String res = endorsementEntityService.addEndorsement(payload,headers.get("authorization"),user);

        //Mail Notification
        MailEntity mail = new MailEntity();
        mail.setMailFrom(user.getEmail());
        mail.setMailTo(user.getEmail());
        mail.setMailSubject("Hurrah !! New Endorsement For You "+ user.getFullName().toUpperCase());
        mail.setMailContent("Someone has endrosed you on the platform!!!\n\nLogin Now to see :)");
        String email = mailService.sendEmail(mail);


        return  ResponseEntity.ok(res+" "+update+" "+email);
    }

    @RequestMapping(value="/getAllQuestionsUserAnswered")
    public ResponseEntity<?> getAllQuestionsUserAnswered(){
        System.out.println("In get all getAllQuestionsUserAnswered api");
        UserEntity user = userEntityService.getDetails();
        List<EndorsementEntity> endorsements = endorsementEntityService.getAllUserEndorsements(user.getId());
        return ResponseEntity.ok(endorsements);
    }
}
