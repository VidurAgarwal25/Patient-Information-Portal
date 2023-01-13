package com.example.patient;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest



class PatientdetailsApplicationTests {
 @Autowired
 PatientdetailsApplication patientdetailsApplication;
 @Test
 void contextLoads() {
 assertTrue(true);
 }
 @Test
 void testComponentProcessingMicroserviceApplication() {
 assertThat(patientdetailsApplication).isNotNull();
}
}
 