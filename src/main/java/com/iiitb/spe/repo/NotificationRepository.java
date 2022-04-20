package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {


    public List<NotificationEntity> findByNotificationTo(long userId);


}
