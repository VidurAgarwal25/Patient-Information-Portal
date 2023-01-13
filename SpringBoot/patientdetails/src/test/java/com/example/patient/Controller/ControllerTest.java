package com.example.patient.Controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import java.io.FileInputStream;
import java.io.IOException;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import com.example.patient.Service.UploadService;
import com.example.patient.entity.UploadDrugDetailsEntity;
import com.example.patient.entity.UploadPatientDetailsEntity;



import com.fasterxml.jackson.databind.ObjectMapper;





@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UploadDetailsController.class)
public class ControllerTest {
 @Autowired
 private MockMvc mockMvc;



 @MockBean
 private UploadService serv;
 @Autowired
UploadDetailsController cont;

@Test
void testComponentProcessingMicroserviceApplication() {
assertThat(cont).isNotNull();
}
@Test
void WhenUploadingExcelFile() throws Exception {
FileInputStream inputFile = new FileInputStream( "C:\\Users\\2112881\\Downloads\\Book1.xlsx"); 
MockMultipartFile file = new MockMultipartFile("file", "Book1", "application/vnd.ms-excel", inputFile);



MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.multipart("/upload").file(file))
.andReturn();



String res=mvcResult.getResponse().getContentAsString();
res=res.substring(0,res.indexOf(" "));
assertEquals("File",res );
}
@Test
void WhenNotUploadingExcelFile() throws Exception {
FileInputStream inputFile = new FileInputStream( "C:\\Users\\2112881\\OneDrive - Cognizant\\Desktop\\Employeefile.csv"); 
MockMultipartFile file = new MockMultipartFile("file", "Book1", "Txt/csv", inputFile);



MvcResult mvcResult=this.mockMvc.perform(MockMvcRequestBuilders.multipart("/upload").file(file))
.andReturn();



assertEquals("Please upload excel file only", mvcResult.getResponse().getContentAsString());
}
@Test
 public void getAllStoreInfoAPI() throws Exception
{	mockMvc.perform( MockMvcRequestBuilders.get("/getdata").accept(MediaType.APPLICATION_JSON))
 .andExpect(status().isOk());
}
 @Test 
void updateStoreAPI() throws Exception
 {
 UploadDrugDetailsEntity dde=new UploadDrugDetailsEntity();
dde.setDrugid("12345-6789-10");
dde.setDrugname("Combiflame");
mockMvc.perform( MockMvcRequestBuilders
.put("/update/{id}", 1)
.content(asJsonString(new UploadPatientDetailsEntity(1,"Vidur Agarwal","Uttar Pradesh","02/25/2000","vidur@gmail.com","9027230167",dde,"Inducted")))
.contentType(MediaType.APPLICATION_JSON)
.accept(MediaType.APPLICATION_JSON))
.andExpect(status().isOk());
}
@Test
void getStoreByIdAPI() throws Exception{
	mockMvc.perform( MockMvcRequestBuilders
.get("/retireve/{id}", 1)
.accept(MediaType.APPLICATION_JSON))
.andExpect(status().isOk());
	}
 static String asJsonString(final Object obj){
	 try {
return new ObjectMapper().writeValueAsString(obj);
} catch (Exception e) {
throw new RuntimeException(e);
}
 }
}

