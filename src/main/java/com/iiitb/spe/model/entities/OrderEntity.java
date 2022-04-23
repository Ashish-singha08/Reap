package com.iiitb.spe.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Orders", schema = "Reap", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "UserId")
    private long userId;
    @Basic
    @Column(name = "OrderedOn")
    private Timestamp orderedOn;
    @Basic
    @Column(name = "ItemId")
    private long itemId;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false , insertable=false, updatable=false)
    private UserEntity userByUserId;

    @ManyToOne
    @JoinColumn(name = "ItemId", referencedColumnName = "Id", nullable = false , insertable=false, updatable=false)
    private ItemEntity itemByItemId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getOrderedOn() {
        return orderedOn;
    }

    public void setOrderedOn(Timestamp orderedOn) {
        this.orderedOn = orderedOn;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id && userId == that.userId && itemId == that.itemId && Objects.equals(orderedOn, that.orderedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, orderedOn, itemId);
    }

    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    public ItemEntity getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(ItemEntity itemByItemId) {
        this.itemByItemId = itemByItemId;
    }
}
