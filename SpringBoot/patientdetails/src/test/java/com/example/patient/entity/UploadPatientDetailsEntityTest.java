package com.example.patient.entity;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



import com.example.patient.entity.UploadDrugDetailsEntity;
import com.example.patient.entity.UploadPatientDetailsEntity;




@SpringBootTest
public class UploadPatientDetailsEntityTest {
 UploadPatientDetailsEntity pde;
 UploadDrugDetailsEntity dde;
 @Test
 void TestUploadPatientDetailsEntity() {
	 	pde=patientdetails();
	 	assertEquals(1,pde.getId());
 }
 @Test
 void testConstructor() {
 dde=new UploadDrugDetailsEntity("12345-6789-10","Combiflame" );
 pde=new UploadPatientDetailsEntity(1, "Vidur Agarwal", "Uttar Pradesh", "02/25/2000", "vidur025agarwal@gmail.com", "9027230167", dde, "Inducted");
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
 