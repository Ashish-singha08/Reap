package com.iiitb.spe.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Notification", schema = "Reap", catalog = "")
public class NotificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "NotificationTo")
    private long notificationTo;
    @Basic
    @Column(name = "NotificationFrom")
    private long notificationFrom;
    @Basic
    @Column(name = "Message")
    private String message;
    @Basic
    @Column(name = "isVisited")
    private int isVisited;
    @Basic
    @Column(name = "isForEndorsement")
    private int isForEndorsement;

    @Basic
    @Column(name = "CreatedOn")
    private Timestamp createdOn;

    @ManyToOne
    @JoinColumn(name = "NotificationTo", referencedColumnName = "Id", nullable = false , insertable=false, updatable=false)
    private UserEntity userByNotificationTo;

    @ManyToOne
    @JoinColumn(name = "NotificationFrom", referencedColumnName = "Id", nullable = false , insertable=false, updatable=false)
    private UserEntity userByNotificationFrom;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNotificationTo() {
        return notificationTo;
    }

    public void setNotificationTo(long notificationTo) {
        this.notificationTo = notificationTo;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public long getNotificationFrom() {
        return notificationFrom;
    }

    public void setNotificationFrom(long notificationFrom) {
        this.notificationFrom = notificationFrom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(int isVisited) {
        this.isVisited = isVisited;
    }

    public int getIsForEndorsement() {
        return isForEndorsement;
    }

    public void setIsForEndorsement(int isForEndorsement) {
        this.isForEndorsement = isForEndorsement;
    }

    public UserEntity getUserByNotificationTo() {
        return userByNotificationTo;
    }

    public void setUserByNotificationTo(UserEntity userByNotificationTo) {
        this.userByNotificationTo = userByNotificationTo;
    }

    public UserEntity getUserByNotificationFrom() {
        return userByNotificationFrom;
    }

    public void setUserByNotificationFrom(UserEntity userByNotificationFrom) {
        this.userByNotificationFrom = userByNotificationFrom;
    }
}
