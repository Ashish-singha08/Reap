package com.iiitb.spe.controller;

import com.iiitb.spe.model.entities.QuestionsEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.services.NotificationEntityService;
import com.iiitb.spe.services.QuestionsEntityService;
import com.iiitb.spe.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/ques")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private UserEntityService userEntityService;
    @Autowired
    private QuestionsEntityService questionsEntityService;

    @Autowired
    private NotificationEntityService notificationEntityService;

    @RequestMapping(value="/getAllQuestionsUserAnswered")
    public ResponseEntity<?> getAllQuestionsUserAnswered(){
        System.out.println("In get all getAllQuestionsUserAnswered api");
        UserEntity user = userEntityService.getDetails();
        List<String[]> questions = questionsEntityService.getAllQuestionsAnsweredByUser(user);
        return ResponseEntity.ok(questions);
    }

    @RequestMapping(value ="/getAllQuestionUserAsked")
    public ResponseEntity<?> getAllQuestionUserAsked(){
        System.out.println("In get all getAllQuestionUserAsked api");
        UserEntity user = userEntityService.getDetails();
        List<String[]> questions = questionsEntityService.getAllQuestionsAskedByUser(user);

        return ResponseEntity.ok(questions);
    }
    @RequestMapping(value ="/getAllQuestionAskedToUser")
    public ResponseEntity<?> getAllQuestionAskedToUser(){
        System.out.println("In get all getAllQuestionUserAsked api");
        UserEntity user = userEntityService.getDetails();
        List<String[]> questions = questionsEntityService.getAllQuestionsAskedToUser(user);
        return ResponseEntity.ok(questions);
    }

    @RequestMapping(value ="/askQuestion", method = RequestMethod.POST)
    public ResponseEntity<?> askQuestion(@RequestBody Map<String,Object> payload, @RequestHeader Map<String,String> headers) throws Exception{
        System.out.println("In ask question api");
        UserEntity user = userEntityService.getDetails();
        String res = questionsEntityService.createQuestion(payload,user);
        Map<String,String> m = new HashMap<>();
        m.put("result",res);
        return ResponseEntity.ok(m);
    }
    @RequestMapping(value ="/updateQuestion", method = RequestMethod.POST)
    public ResponseEntity<?> updateQuestion(@RequestBody Map<String,Object> payload, @RequestHeader Map<String,String> headers) throws Exception{
        System.out.println("In update question api");
        UserEntity user = userEntityService.getDetails();
        String res = questionsEntityService.updateForwardedByUser(payload,user);
        Map<String,String> m = new HashMap<>();
        m.put("result",res);
        return ResponseEntity.ok(m);

    }
    @RequestMapping(value ="/answerQuestion", method = RequestMethod.POST)
    public ResponseEntity<?> answerQuestion(@RequestBody Map<String,Object> payload, @RequestHeader Map<String,String> headers) throws Exception{
        System.out.println("In answer question api");
        UserEntity user = userEntityService.getDetails();
        String res = questionsEntityService.updateAnsweredByUser(payload,user);
        Map<String,String> m = new HashMap<>();
        m.put("result",res);
        return ResponseEntity.ok(m);

    }
}
