package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionsEntity,Long> {

    QuestionsEntity findById(long userId);
    List<QuestionsEntity> findByAskedByUserId (long userId);
    @Query(value="SELECT * FROM Questions where AskedToUserId=?1 And AnsweredByUserId IS NULL",nativeQuery = true)
    List<QuestionsEntity> findByAskedToUserId (long  userId);

    List<QuestionsEntity> findByAnsweredByUserId (long userId);

    @Transactional
    @Modifying
    @Query(value ="UPDATE Questions SET ForwardedByUserId =?3,  AskedToUserId = ?2  where Id=?1",nativeQuery = true)
    void updateForwardedBy (long questionId, long toAnswerUserId, long forwardedUserId);

    @Transactional
    @Modifying
    @Query(value ="UPDATE Questions SET AnsweredByUserId =?2, Answer=?3 where Id=?1",nativeQuery = true)
    void updateAnsweredBy (long questionId, long userId, String answer);
}
