package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.EndorsementEntity;
import com.iiitb.spe.model.entities.NotificationEntity;
import com.iiitb.spe.model.entities.UserEntity;
import com.iiitb.spe.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
    public String createNotification (Map<String,Object> payload, UserEntity user, int flag, String name){
        try{
            NotificationEntity notification = new NotificationEntity();
            notification.setNotificationTo(Long.valueOf((String) payload.get("userId")));
            notification.setNotificationFrom(user.getId());
            notification.setIsForEndorsement(flag);
            notification.setIsVisited(0);
            Date date = new Date();
            notification.setCreatedOn(new Timestamp(date.getTime()));
            if(flag==1){
              notification.setMessage(name+" Endorsed you :)");
            }
            else{
                notification.setMessage("Someone Questioned");
            }
            NotificationEntity newNotification = notificationRepository.save(notification);
            System.out.println(newNotification.getId());
            return "Ok";
        }
        catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
