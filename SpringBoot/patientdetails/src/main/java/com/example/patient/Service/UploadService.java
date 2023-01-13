package com.example.patient.Service;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.patient.Helper.ExcelHelper;
import com.example.patient.entity.UploadPatientDetailsEntity;
import com.example.patient.repository.UploadPatientDetailsRepo;

@Service

public class UploadService {
	@Autowired
	private UploadPatientDetailsRepo repo;

	public UploadService(UploadPatientDetailsRepo repo2) {

		this.repo = repo2;
	}

	public String save(MultipartFile file) throws IOException {
		List<UploadPatientDetailsEntity> list = ExcelHelper.convertExceltoList(file.getInputStream());
		this.repo.saveAll(list);

		if (list.size() != 0)
			return " AND INDUCTION IS SUCCESSFUL";
		return "BUT INDUCTION HAS BEEN FAILED BECAUSE OF INCORRECT DETAILS";
	}

	public List<UploadPatientDetailsEntity> getAll() {
		return this.repo.findAll();
	}

	public List<UploadPatientDetailsEntity> setStatus(String patientname) {

		List<UploadPatientDetailsEntity> l = this.repo.findAllByPatientname(patientname);
		for (int i = 0; i < l.size(); i++) {
			UploadPatientDetailsEntity u = l.get(i);
			u.setStatus("Processed");
			this.repo.save(u);
		}
		return this.repo.findAllByPatientname(patientname);
	}

public String updateById(int id, UploadPatientDetailsEntity p){
	UploadPatientDetailsEntity pat = repo.findById(id);
if (pat != null) {
 if (!p.getAddress().equalsIgnoreCase("")) {
  pat.setAddress(p.getAddress());
 }


if (!p.getDob().equalsIgnoreCase("")) {
if (Pattern.matches("^(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d$", p.getDob()))
pat.setDob(p.getDob());
else
return "Invalid Date Of Birth";
}



 if (!p.getEmail().equalsIgnoreCase("")) {
  if (Pattern.matches("^(.+)@(.+)$", p.getEmail()))
 pat.setEmail(p.getEmail());
 else
return "Invalid Email Address";
 }

 if (!p.getPhn().equalsIgnoreCase("")) {
 if (Pattern.matches("[0-9]{10}", p.getPhn()))
 pat.setPhn(p.getPhn());
 else
 return "Invalid Phone Number";
 }
 repo.save(pat);
 return "Successfully Updated Details";
 }
return "Patient Not Found";
} 
}