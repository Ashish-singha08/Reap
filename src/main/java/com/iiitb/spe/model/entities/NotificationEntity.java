package com.iiitb.spe.model.entities;

import javax.persistence.*;
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
    @Column(name = "Flag")
    private int flag;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationEntity that = (NotificationEntity) o;
        return id == that.id && notificationTo == that.notificationTo && notificationFrom == that.notificationFrom && flag == that.flag && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationTo, notificationFrom, message, flag);
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
