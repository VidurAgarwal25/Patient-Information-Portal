package com.example.patient.repository;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
 
import java.util.ArrayList;
import java.util.List;
 
import javax.transaction.Transactional;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
 
import com.example.patient.entity.UploadDrugDetailsEntity;
import com.example.patient.entity.UploadPatientDetailsEntity;
import com.example.patient.repository.UploadDrugDetailsRepo;
import com.example.patient.repository.UploadPatientDetailsRepo;
 
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class PatientDetailsRepoTest {
 
    @Autowired
    UploadPatientDetailsRepo repo;
 
    @Autowired
    UploadDrugDetailsRepo drugrepo;
    private UploadPatientDetailsEntity pde = new UploadPatientDetailsEntity();
 
    private UploadDrugDetailsEntity dde = new UploadDrugDetailsEntity();
 
    @Test
    void contextLoads() {
        assertThat(pde).isNotNull();
    }
 
    @Test
    void contextLoadsDrug() {
        assertThat(dde).isNotNull();
    }
 
    
    @Test
    void TestfindByName() {
        pde = patientdetails();
        repo.save(pde);
        List<UploadPatientDetailsEntity> list = new ArrayList<>();
        list = repo.findAllByPatientname("Vidur Agarwal");
        assertEquals(list.size(), 1);
    }
 
    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }
 
    UploadPatientDetailsEntity patientdetails() {
        pde = new UploadPatientDetailsEntity();
        dde = drugdetails();
        pde.setAddress("Uttar Pradesh");
        pde.setDob("02/25/2000");
        pde.setDrug(dde);
        pde.setEmail("vidur025agarwal@gmail.com");
        pde.setId(1);
        pde.setPatientname("Vidur Agarwal");
        pde.setPhn("9027230167");
        pde.setStatus("Inducted");
        return pde;
    }
 
    UploadDrugDetailsEntity drugdetails() {
        dde = new UploadDrugDetailsEntity();
        dde.setDrugid("12345-6789-10");
        dde.setDrugname("Combiflame");
        return dde;
    }
}

