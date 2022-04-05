package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.EndorsementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EndorsementRepository extends JpaRepository<EndorsementEntity,Integer> {

     @Query(value="SELECT * FROM Endorsement where GiverId=?1",nativeQuery = true)
     List<EndorsementEntity> findAllEndorsementsByGiverId(long userId);

     @Query(value="SELECT * FROM Endorsement where TakerId=?1",nativeQuery = true)
     List<EndorsementEntity> findAllEndorsementsByTakerId(long userId);
}
