package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.NotificationEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NotificationEntityService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationEntity> getAllUserNotification(UserEntity user){

        long userId = user.getId();
        List<NotificationEntity> answer = notificationRepository.findByNotificationTo(userId);
        return answer;
    }
    public String createNotification (Map<String,Object> payload, UserEntity user, int flag){
        try{
            NotificationEntity notification = new NotificationEntity();
            notification.setNotificationTo((long)(payload.get("answerId")));
            notification.setNotificationFrom(user.getId());
            notification.setFlag(flag);
            if(flag==0){
              notification.setMessage("Someone Endorsed");
            }
            else{
                notification.setMessage("Someone Questioned");
            }
            return "Ok";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
