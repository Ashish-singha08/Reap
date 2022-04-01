package com.iiitb.spe.repo;

import com.iiitb.spe.model.entities.EndorsementEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EndorsementRepository extends JpaRepository<EndorsementEntity,Integer> {

   // @Query(value="SELECT * FROM Patient P INNER JOIN UserPermissionPatient UPP ON P.Id = UPP.PatientId WHERE UPP.UserId = ?1 AND UPP.CanView=1",nativeQuery = true)
    //List<PatientEntity> findAllPatientByUser(long id);

    //List<PatientEntity> findById(long id);

//    List<PatientEntity> findByAbhaId(String abhaId);
//
//    List<PatientEntity> findByFirstName(String firstName);
//
//    List<PatientEntity> findByPhoneNumber(String phoneNumber);
}
