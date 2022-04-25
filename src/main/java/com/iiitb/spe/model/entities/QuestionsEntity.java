package com.iiitb.spe.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Questions", schema = "Reap", catalog = "")
public class QuestionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private long id;
    @Basic
    @Column(name = "Question")
    private String question;
    @Basic
    @Column(name = "AskedByUserId")
    private long askedByUserId;
    @Basic
    @Column(name = "AskedToUserId")
    private long askedToUserId;
    @Basic
    @Column(name = "AnsweredByUserId")
    private Long answeredByUserId;
    @Basic
    @Column(name = "ForwardedByUserId")
    private Long forwardedByUserId;
    @Basic
    @Column(name = "Answer")
    private String answer;

    @Basic
    @Column(name="AskedOn")
    private Timestamp askedOn;
    @ManyToOne
    @JoinColumn(name = "AskedByUserId", referencedColumnName = "Id", nullable = false,insertable=false, updatable=false)
    private UserEntity userByAskedByUserId;

    @ManyToOne
    @JoinColumn(name = "AskedToUserId", referencedColumnName = "Id", nullable = false, insertable=false, updatable=false)
    private UserEntity userByAskedToUserId;

    @ManyToOne
    @JoinColumn(name = "AnsweredByUserId", referencedColumnName = "Id", insertable=false, updatable=false)
    private UserEntity userByAnsweredByUserId;

    @ManyToOne
    @JoinColumn(name = "ForwardedByUserId", referencedColumnName = "Id", insertable=false, updatable=false)
    private UserEntity userByForwardedByUserId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getAskedOn() {
        return askedOn;
    }

    public void setAskedOn(Timestamp askedOn) {
        this.askedOn = askedOn;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public long getAskedByUserId() {
        return askedByUserId;
    }

    public void setAskedByUserId(long askedByUserId) {
        this.askedByUserId = askedByUserId;
    }

    public long getAskedToUserId() {
        return askedToUserId;
    }

    public void setAskedToUserId(long askedToUserId) {
        this.askedToUserId = askedToUserId;
    }

    public Long getAnsweredByUserId() {
        return answeredByUserId;
    }

    public void setAnsweredByUserId(Long answeredByUserId) {
        this.answeredByUserId = answeredByUserId;
    }

    public Long getForwardedByUserId() {
        return forwardedByUserId;
    }

    public void setForwardedByUserId(Long forwardedByUserId) {
        this.forwardedByUserId = forwardedByUserId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionsEntity that = (QuestionsEntity) o;
        return id == that.id && askedByUserId == that.askedByUserId && askedToUserId == that.askedToUserId && Objects.equals(question, that.question) && Objects.equals(answeredByUserId, that.answeredByUserId) && Objects.equals(forwardedByUserId, that.forwardedByUserId) && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, askedByUserId, askedToUserId, answeredByUserId, forwardedByUserId, answer);
    }

    public UserEntity getUserByAskedByUserId() {
        return userByAskedByUserId;
    }

    public void setUserByAskedByUserId(UserEntity userByAskedByUserId) {
        this.userByAskedByUserId = userByAskedByUserId;
    }

    public UserEntity getUserByAskedToUserId() {
        return userByAskedToUserId;
    }

    public void setUserByAskedToUserId(UserEntity userByAskedToUserId) {
        this.userByAskedToUserId = userByAskedToUserId;
    }

    public UserEntity getUserByAnsweredByUserId() {
        return userByAnsweredByUserId;
    }

    public void setUserByAnsweredByUserId(UserEntity userByAnsweredByUserId) {
        this.userByAnsweredByUserId = userByAnsweredByUserId;
    }

    public UserEntity getUserByForwardedByUserId() {
        return userByForwardedByUserId;
    }

    public void setUserByForwardedByUserId(UserEntity userByForwardedByUserId) {
        this.userByForwardedByUserId = userByForwardedByUserId;
    }
}
