package com.example.patient.repository;



import java.util.List;



import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.example.patient.entity.UploadPatientDetailsEntity;
@Repository
public interface UploadPatientDetailsRepo extends JpaRepository<UploadPatientDetailsEntity,Integer> {



 UploadPatientDetailsEntity findById(int id);
 @Query("Select * from UploadPatientDetailsEntity where patientname= ?1")
 List<UploadPatientDetailsEntity> findAllByPatientname(String patientname);
}
 