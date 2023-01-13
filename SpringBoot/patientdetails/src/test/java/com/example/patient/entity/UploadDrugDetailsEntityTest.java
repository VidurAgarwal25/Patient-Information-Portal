package com.example.patient.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.example.patient.entity.UploadDrugDetailsEntity;
public class UploadDrugDetailsEntityTest {
	UploadDrugDetailsEntity dde;
 @Test
 void testEntity() {
 dde=drugdetails();
 assertEquals("12345-6789-10",dde.getDrugid());
 assertEquals("Combiflame", dde.getDrugname());
 }
 @Test
 void testConstructor() {
 dde=new UploadDrugDetailsEntity("12345-6789-10", "Combiflame");
 assertEquals("12345-6789-10",dde.getDrugid());
 assertEquals("Combiflame", dde.getDrugname());
 }
 UploadDrugDetailsEntity drugdetails() {
 dde=new UploadDrugDetailsEntity();
 dde.setDrugid("12345-6789-10");
 dde.setDrugname("Combiflame");
 return dde;
}
}
 