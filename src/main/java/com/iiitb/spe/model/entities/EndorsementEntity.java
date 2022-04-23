package com.iiitb.spe.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Endorsement", schema = "Reap", catalog = "")
public class EndorsementEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "GiverId")
    private long giverId;
    @Basic
    @Column(name = "TakerId")
    private long takerId;
    @Basic
    @Column(name = "EndorsedOn")
    private Timestamp endorsedOn;
    @Basic
    @Column(name = "Message")
    private String message;
    @Basic
    @Column(name = "CoinsEndorsed")
    private int coinsEndorsed;
    @Basic
    @Column(name = "TagId")
    private long tagId;

    @ManyToOne
    @JoinColumn(name = "GiverId", referencedColumnName = "Id", nullable = false , insertable=false, updatable=false)
    private UserEntity userByGiverId;

    @ManyToOne
    @JoinColumn(name = "TakerId", referencedColumnName = "Id", nullable = false , insertable=false, updatable=false)
    private UserEntity userByTakerId;

    @ManyToOne
    @JoinColumn(name = "TagId", referencedColumnName = "Id", nullable = false, insertable=false, updatable=false)
    private TagEntity tagByTagId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGiverId() {
        return giverId;
    }

    public void setGiverId(long giverId) {
        this.giverId = giverId;
    }

    public long getTakerId() {
        return takerId;
    }

    public void setTakerId(long takerId) {
        this.takerId = takerId;
    }

    public Timestamp getEndorsedOn() {
        return endorsedOn;
    }

    public void setEndorsedOn(Timestamp endorsedOn) {
        this.endorsedOn = endorsedOn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCoinsEndorsed() {
        return coinsEndorsed;
    }

    public void setCoinsEndorsed(int coinsEndorsed) {
        this.coinsEndorsed = coinsEndorsed;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndorsementEntity that = (EndorsementEntity) o;
        return id == that.id && giverId == that.giverId && takerId == that.takerId && coinsEndorsed == that.coinsEndorsed && tagId == that.tagId && Objects.equals(endorsedOn, that.endorsedOn) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giverId, takerId, endorsedOn, message, coinsEndorsed, tagId);
    }

    public UserEntity getUserByGiverId() {
        return userByGiverId;
    }

    public void setUserByGiverId(UserEntity userByGiverId) {
        this.userByGiverId = userByGiverId;
    }

    public UserEntity getUserByTakerId() {
        return userByTakerId;
    }

    public void setUserByTakerId(UserEntity userByTakerId) {
        this.userByTakerId = userByTakerId;
    }

    public TagEntity getTagByTagId() {
        return tagByTagId;
    }

    public void setTagByTagId(TagEntity tagByTagId) {
        this.tagByTagId = tagByTagId;
    }
}
