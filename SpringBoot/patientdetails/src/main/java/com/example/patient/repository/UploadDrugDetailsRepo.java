package com.example.patient.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.example.patient.entity.UploadDrugDetailsEntity;
@Repository
public interface UploadDrugDetailsRepo extends JpaRepository<UploadDrugDetailsEntity,String>{
    
}