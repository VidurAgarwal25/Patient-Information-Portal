package com.example.patient.Controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import com.example.patient.Helper.ExcelHelper;
import com.example.patient.Service.UploadService;
import com.example.patient.entity.UploadPatientDetailsEntity;



@RestController
@CrossOrigin("*")
public class UploadDetailsController {
@Autowired
private UploadService serv;



@PostMapping("/upload")
public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {


 if (ExcelHelper.checkExcelFormat(file)) {
 String resp = this.serv.save(file);
 return ResponseEntity.ok("File is uploaded Successfully" + resp);
 }
return ResponseEntity.ok("Please upload excel file only");
}



@GetMapping("/getdata")
public ResponseEntity<List<UploadPatientDetailsEntity>> getAll() {
List<UploadPatientDetailsEntity> list = this.serv.getAll();
return ResponseEntity.ok(list);
}



 @PutMapping("/update/{id}")
public ResponseEntity<String> updateAddressById(@PathVariable(value = "id") int id,@RequestBody UploadPatientDetailsEntity p) {
 String resp = serv.updateById(id, p);
 if(resp==null)
 resp="Invalid";
 if (resp.equalsIgnoreCase("Invalid Date Of Birth"))
 return ResponseEntity.ok("Invalid Date Of Birth");
 if (resp.equalsIgnoreCase("Invalid Email Address"))
 return ResponseEntity.ok("Invalid Email Address");
 if (resp.equalsIgnoreCase("Invalid Phone Number"))
 return ResponseEntity.ok("Invalid Phone Number");
 if (resp.equalsIgnoreCase("Successfully Updated Details"))
return ResponseEntity.ok("Successfully Updated Details");
return ResponseEntity.ok("Patient Not Found");
}



@GetMapping("/retireve/{patientname}")
public ResponseEntity<List<UploadPatientDetailsEntity>> getPatientByName(
@PathVariable(value = "patientname") String patientname) {
List<UploadPatientDetailsEntity> list = new ArrayList<>();
list = this.serv.setStatus(patientname);
return ResponseEntity.ok(list);
}
}
 