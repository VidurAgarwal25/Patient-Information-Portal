
package com.example.patient.Service;
 
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.example.patient.entity.UploadDrugDetailsEntity;
import com.example.patient.entity.UploadPatientDetailsEntity;
import com.example.patient.repository.UploadPatientDetailsRepo;
 
 

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {
    @Mock
    private UploadPatientDetailsRepo repo;
    @InjectMocks
    private UploadService uploadService;
    UploadDrugDetailsEntity dde;
    UploadPatientDetailsEntity pde;
    @BeforeEach
    void setUp() {
        this.uploadService = new UploadService(this.repo);
    }
    @AfterEach
    void tearDown() {
        repo.deleteAll();
 
    }
 
    @Test
    void testComponentProcessingMicroserviceApplication() {
        assertThat(uploadService).isNotNull();
    }
    @Test
    public void testLegitimateFile() throws IOException {
        FileInputStream inputFile = new FileInputStream( "C:\\Users\\2112881\\Downloads\\Book1.xlsx");  
        MockMultipartFile file = new MockMultipartFile("file", "Book1", "multipart/form-data", inputFile);
        String msg=uploadService.save(file);
        assertThat(msg).isEqualTo(" AND INDUCTION IS SUCCESSFUL");
    }
    @Test
    public void testEmptyFile() throws IOException {
        FileInputStream inputFile = new FileInputStream( "C:\\Users\\2112881\\OneDrive - Cognizant\\Desktop\\Book1Empty.xlsx");  
        MockMultipartFile file = new MockMultipartFile("file", "Book1Empty", "multipart/form-data", inputFile);
        String msg=uploadService.save(file);
        assertThat(msg).isEqualTo("BUT INDUCTION HAS BEEN FAILED BECAUSE OF INCORRECT DETAILS");
    }
    @Test
    void testGetAll(){
        pde=patientdetails();
        given(repo.findAll()).willReturn(List.of(pde));
        List<UploadPatientDetailsEntity>list=uploadService.getAll();
        assertThat(list.size()).isEqualTo(1);
    }
    @Test
    void testStatus() {
        pde=patientdetails();
        given(repo.findAllByPatientname(pde.getPatientname())).willReturn(List.of(pde));
        List<UploadPatientDetailsEntity>list=uploadService.setStatus(pde.getPatientname());
        assertThat(list.size()).isEqualTo(1);
    }
    @Test
    void testUpdateExistingDetails() {
        pde=patientdetails();
        given(repo.findById(pde.getId())).willReturn(pde);
        String res=uploadService.updateById(pde.getId(),pde);
        assertThat(res).isEqualTo("Successfully Updated Details");
    }
    @Test
    void testUpdateNotExistingDetails() {
        pde=patientdetails();
        given(repo.findById(101)).willReturn(null);
        String res=uploadService.updateById(101,pde);
        assertThat(res).isEqualTo("Patient Not Found");
    }
    @Test
    void testUpdateInvalidDateOfBirth() {
        pde=patientdetails();
        pde.setDob("35-18-2000");
        given(repo.findById(pde.getId())).willReturn(pde);
        String res=uploadService.updateById(pde.getId(),pde);
        assertThat(res).isEqualTo("Invalid Date Of Birth");
    }
    @Test
    void testUpdateInvalidEmailAddress() {
        pde=patientdetails();
        pde.setEmail("@agarwal");
        given(repo.findById(pde.getId())).willReturn(pde);
        String res=uploadService.updateById(pde.getId(),pde);
        assertThat(res).isEqualTo("Invalid Email Address");
    }
    @Test
    void testUpdateInvalidPhoneNumber() {
        pde=patientdetails();
        pde.setPhn("+91 89");
        given(repo.findById(pde.getId())).willReturn(pde);
        String res=uploadService.updateById(pde.getId(),pde);
        assertThat(res).isEqualTo("Invalid Phone Number");
    }
    UploadPatientDetailsEntity patientdetails() {
        pde=new UploadPatientDetailsEntity();
        dde=drugdetails();
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
        dde=new UploadDrugDetailsEntity();
        dde.setDrugid("12345-6789-10");
        dde.setDrugname("Combiflame");
        return dde;
    }
}

