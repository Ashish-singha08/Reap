package com.iiitb.spe.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iiitb.spe.model.entities.QuestionsEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.QuestionRepository;
import com.iiitb.spe.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsEntityService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<String[]> createArray(List<QuestionsEntity>a){
        List<String[]> ans = new ArrayList<>();
        for(int i=0;i<a.size();i++){

            String s[] = {a.get(i).getQuestion(),a.get(i).getUserByAskedByUserId().getFullName(),
                    a.get(i).getAskedOn().toString(),String.valueOf(a.get(i).getId()), a.get(i).getAnswer()};
            ans.add(s);
        }
            return ans;
    }

    public List<String[]> getAllQuestionsAskedByUser(UserEntity user){
        long userId = user.getId();
        List<QuestionsEntity> answer = questionRepository.findByAskedByUserId(userId);
        return createArray(answer);
    }
    public List<String[]> getAllQuestionsAskedToUser(UserEntity user){
        long userId = user.getId();
        List<QuestionsEntity> answer = questionRepository.findByAskedToUserId(userId);

        return createArray(answer);
    }
    public List<String[]> getAllQuestionsAnsweredByUser(UserEntity user){
        long userId = user.getId();
        List<QuestionsEntity> answer = questionRepository.findByAnsweredByUserId(userId);
        return createArray(answer);
    }
    public String updateAnsweredByUser(Map<String,Object> payload,UserEntity user){
        long userId = user.getId();
        String answer= (String)payload.get("Answer");
        long qId=  Long.valueOf((String)payload.get("QuestionId"));
        questionRepository.updateAnsweredBy(qId,userId,answer);
        userRepository.updateCoins(user.getCoinBalance()+20,userId);
        return "Answer Done";
    }
    public String updateForwardedByUser(Map<String,Object> payload,UserEntity user){
        long userId = user.getId();
        long qId= Long.valueOf((String)payload.get("QuestionId"));

        long userToAnswerId = Long.valueOf((String)payload.get("ForwardedToUserId"));
        questionRepository.updateForwardedBy(qId,userToAnswerId,userId);

        return "ForwardedDone";
    }

    public String createQuestion(Map<String,Object> payload,UserEntity user){
        try{
            QuestionsEntity question = new QuestionsEntity();
            question.setQuestion((String)payload.get("Question"));
            question.setAskedByUserId(user.getId());
            question.setAnswer("a");
            question.setAskedToUserId(Long.valueOf((String) payload.get("answerId")));
            Date date = new Date();
            question.setAskedOn(new Timestamp(date.getTime()));
            QuestionsEntity ques = questionRepository.save(question);
            return "Done";
        }
         catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
