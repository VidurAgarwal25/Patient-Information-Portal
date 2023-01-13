package com.example.patient.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UploadPatientDetailsEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String patientname;
private String address;
private String dob;
private String email;
private String phn;
@ManyToOne(cascade=CascadeType.ALL)
private UploadDrugDetailsEntity drug;
public UploadPatientDetailsEntity(){
 }
public UploadPatientDetailsEntity(int id, String patientname, String address, String dob, String email, String phn,UploadDrugDetailsEntity drug, String status){
super();
this.id = id;
this.patientname = patientname;
this.address = address;
this.dob = dob;
this.email = email;
this.phn = phn;
this.drug = drug;
this.status = status;
}
 public UploadDrugDetailsEntity getDrug() {
 return drug;
 }
 public void setDrug(UploadDrugDetailsEntity drug) {
 this.drug = drug;
 }

 private String status;
public void setId(int id)
  {
this.id = id;
}
public int getId() {return id;}

public String getPatientname()
  {return patientname;}

public void setPatientname(String patientname)
   {this.patientname = patientname;}
public String getAddress()
    {return address;}
public void setAddress(String address)
     {this.address = address;}
public String getDob()
{return dob;}
public void setDob(String dob)
       {this.dob = dob;}
public String getEmail() {return email;}
public void setEmail(String email) {this.email = email;}
public String getPhn() {return phn;}
public void setPhn(String d) {
this.phn = d;}
public String getStatus()
        {return status;}
public void setStatus(String status)
{this.status = status;
}
}


