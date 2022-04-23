package com.iiitb.spe.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "Reap", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "Username")
    private String username;
    @Basic
    @Column(name = "FullName")
    private String fullName;
    @Basic
    @Column(name = "RoleTypeId")
    private String roleTypeId;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Basic
    @Column(name = "Password")
    private String password;
    @Basic
    @Column(name = "CoinBalance")
    private int coinBalance;
    @JsonIgnore
    @OneToMany(mappedBy = "userByGiverId")
    private Collection<EndorsementEntity> endorsementsById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByTakerId")
    private Collection<EndorsementEntity> endorsementsById_0;
    @JsonIgnore
    @OneToMany(mappedBy = "userByNotificationTo")
    private Collection<NotificationEntity> notificationsById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByNotificationFrom")
    private Collection<NotificationEntity> notificationsById_0;
    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId")
    private Collection<OrderEntity> ordersById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByAskedByUserId")
    private Collection<QuestionsEntity> questionsById;
    @JsonIgnore
    @OneToMany(mappedBy = "userByAskedToUserId")
    private Collection<QuestionsEntity> questionsById_0;
    @JsonIgnore
    @OneToMany(mappedBy = "userByAnsweredByUserId")
    private Collection<QuestionsEntity> questionsById_1;
    @JsonIgnore
    @OneToMany(mappedBy = "userByForwardedByUserId")
    private Collection<QuestionsEntity> questionsById_2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(String roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(int coinBalance) {
        this.coinBalance = coinBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && coinBalance == that.coinBalance && Objects.equals(username, that.username) && Objects.equals(fullName, that.fullName) && Objects.equals(roleTypeId, that.roleTypeId) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName, roleTypeId, email, phoneNumber, password, coinBalance);
    }

    public Collection<EndorsementEntity> getEndorsementsById() {
        return endorsementsById;
    }

    public void setEndorsementsById(Collection<EndorsementEntity> endorsementsById) {
        this.endorsementsById = endorsementsById;
    }

    public Collection<EndorsementEntity> getEndorsementsById_0() {
        return endorsementsById_0;
    }

    public void setEndorsementsById_0(Collection<EndorsementEntity> endorsementsById_0) {
        this.endorsementsById_0 = endorsementsById_0;
    }

    public Collection<NotificationEntity> getNotificationsById() {
        return notificationsById;
    }

    public void setNotificationsById(Collection<NotificationEntity> notificationsById) {
        this.notificationsById = notificationsById;
    }

    public Collection<NotificationEntity> getNotificationsById_0() {
        return notificationsById_0;
    }

    public void setNotificationsById_0(Collection<NotificationEntity> notificationsById_0) {
        this.notificationsById_0 = notificationsById_0;
    }

    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }

    public Collection<QuestionsEntity> getQuestionsById() {
        return questionsById;
    }

    public void setQuestionsById(Collection<QuestionsEntity> questionsById) {
        this.questionsById = questionsById;
    }

    public Collection<QuestionsEntity> getQuestionsById_0() {
        return questionsById_0;
    }

    public void setQuestionsById_0(Collection<QuestionsEntity> questionsById_0) {
        this.questionsById_0 = questionsById_0;
    }

    public Collection<QuestionsEntity> getQuestionsById_1() {
        return questionsById_1;
    }

    public void setQuestionsById_1(Collection<QuestionsEntity> questionsById_1) {
        this.questionsById_1 = questionsById_1;
    }

    public Collection<QuestionsEntity> getQuestionsById_2() {
        return questionsById_2;
    }

    public void setQuestionsById_2(Collection<QuestionsEntity> questionsById_2) {
        this.questionsById_2 = questionsById_2;
    }
}
