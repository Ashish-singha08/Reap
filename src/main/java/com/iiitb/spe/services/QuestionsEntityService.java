package com.iiitb.spe.services;

import java.util.List;
import java.util.Map;

import com.iiitb.spe.model.entities.QuestionsEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionsEntityService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionsEntity> getAllQuestionsAskedByUser(UserEntity user){
        long userId = user.getId();
        List<QuestionsEntity> answer = questionRepository.findByAskedByUserId(userId);
        return answer;
    }
    public List<QuestionsEntity> getAllQuestionsAskedToUser(UserEntity user){
        long userId = user.getId();
        List<QuestionsEntity> answer = questionRepository.findByAskedToUserId(userId);
        return answer;
    }
    public List<QuestionsEntity> getAllQuestionsAnsweredByUser(UserEntity user){
        long userId = user.getId();
        List<QuestionsEntity> answer = questionRepository.findByAnsweredByUserId(userId);
        return answer;
    }
    public String updateAnsweredByUser(Map<String,Object> payload,UserEntity user){
        long userId = user.getId();
        String answer="";
        long qId=1;
        questionRepository.updateAnsweredBy(qId,userId,answer);
        return "Done";
    }
    public String updateForwardedByUser(Map<String,Object> payload,UserEntity user){
        long userId = user.getId();
        long qId=1;
        long userToAnswerId = (long)payload.get("AnswerId");
        String answer="";

        questionRepository.updateForwardedBy(qId,userId,userToAnswerId);
        return "Done";
    }

    public String createQuestion(Map<String,Object> payload,UserEntity user){
        try{
            QuestionsEntity question = new QuestionsEntity();
            question.setQuestion((String)payload.get("Question"));
            question.setAskedByUserId(user.getId());
            question.setAskedToUserId((long)(payload.get("answerId")));
            QuestionsEntity ques = questionRepository.save(question);
            return "Done";
        }
         catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }

    }
}
