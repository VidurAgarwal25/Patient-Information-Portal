package com.example.patient.entity;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLInsert;

@Entity
@SQLInsert(sql = "Insert INTO upload_drug_details_entity (drugname,drugid) " +
"VALUES (?, ?)"+" ON DUPLICATE KEY UPDATE drugname=" + "VALUES(drugname)" )
public class UploadDrugDetailsEntity {
	public UploadDrugDetailsEntity(String drugid, String drugname) {
		this.drugid = drugid;
		this.drugname = drugname;
		}
	public UploadDrugDetailsEntity() {
    super();
    }
	@Id
	private String drugid;
	private String drugname;
	public String getDrugid()
	{return drugid;}
	public void setDrugid(String drugid) {
	this.drugid = drugid;
	}
	public String getDrugname() {
		return drugname;
		}
	public void setDrugname(String drugname) {
		this.drugname = drugname;
		}
}


