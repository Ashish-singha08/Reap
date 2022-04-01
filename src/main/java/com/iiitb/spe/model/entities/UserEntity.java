package com.iiitb.spe.model.entities;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Basic
    @Column(name = "CoinBalance")
    private int coinBalance;
    public int getCoinsBalance() {
        return coinBalance;
    }

    public void setCoinsBalance(int coinBalance) {
        this.coinBalance = coinBalance;
    }

    @OneToMany(mappedBy = "userByGiverId")
    private Collection<EndorsementEntity> endorsementsById;
    @OneToMany(mappedBy = "userByTakerId")
    private Collection<EndorsementEntity> endorsementsById_0;
    @OneToMany(mappedBy = "userByUserId")
    private Collection<OrderEntity> ordersById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(fullName, that.fullName) && Objects.equals(roleTypeId, that.roleTypeId) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, fullName, roleTypeId, email, phoneNumber);
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

    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
