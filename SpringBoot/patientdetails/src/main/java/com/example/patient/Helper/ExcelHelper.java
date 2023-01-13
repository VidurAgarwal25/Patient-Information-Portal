package com.example.patient.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.regex.Pattern;

import javax.persistence.Entity;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.patient.entity.UploadDrugDetailsEntity;
import com.example.patient.entity.UploadPatientDetailsEntity;
import com.example.patient.repository.UploadDrugDetailsRepo;
import com.example.patient.repository.UploadPatientDetailsRepo;

public class ExcelHelper {
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
			return true;
		else
			return false;
	}

public static List<UploadPatientDetailsEntity> convertExceltoList(InputStream is)throws IOException{
	List<UploadPatientDetailsEntity> list=new ArrayList<>();
	
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rownum=0;
		Iterator<Row> iterator=sheet.iterator();
		UploadPatientDetailsEntity pde=new UploadPatientDetailsEntity();
		UploadDrugDetailsEntity dde= new UploadDrugDetailsEntity();
		HSSFDataFormatter hdf = new HSSFDataFormatter();
		while(iterator.hasNext()) {
			Row row=iterator.next();
			if(rownum==0) {
				rownum++;
				continue;
}
			Iterator<Cell> cells=row.iterator();
			int cid=0;
			int f=0;
			while(cells.hasNext()) {
			Cell cell=cells.next();
			switch(cid) {
			case 0:
				//System.out.println(cell.getCellType());
				String name;
				name=cell.getStringCellValue();
				if(name.length()>=5&&name.length()<=20)
			{
					if(Pattern.matches("[A-Za-z\\s]+", name)){
						pde.setPatientname(cell.getStringCellValue());
					}
			}
						
				else
					f=1;
				//System.out.println(pde.getPatientname());
					break;
				case 1:
					pde.setAddress(cell.getStringCellValue());
					//System.out.println(pde.getAddress());
					break;
				case 2:
					String d=cell.toString();
					
					if(Pattern.matches("^(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d$", d))
						pde.setDob(cell.toString());
					else
					{
						f=1;
						System.out.println("working");
						}
					break;
					case 3:
						//pde.setEmail("check@gmail.com");
						String e=cell.getStringCellValue();
						if(Pattern.matches("^(.+)@(.+)$", e))
							pde.setEmail(e);
						else
							f=1;
						//System.out.println(pde.getEmail());
						break;
						case 4:
							//System.out.println(cell.getCellType());
							//pde.setPhn("9027230167");
							String p=hdf.formatCellValue(cell);
							if(Pattern.matches("[0-9]{10}", p))
								pde.setPhn(p);
							else
								f=1;
							//System.out.println(pde.getPhn());
							break;
							case 5:
								//dde.setDrugid("96100899860");
								//System.out.println("test for drugid");
								String did=hdf.formatCellValue(cell);
								if(Pattern.matches("[0-9]{5}-[0-9]{4}-[0-9]{2}", did))
									dde.setDrugid(did);
								else
									{f=1;
									//dde.setDrugid("9027");
									}//
								//System.out.println(dde.getDrugid());
								break;
							case 6://dde.setDrugname("combiflame");
									dde.setDrugname(cell.getStringCellValue());
									if(f==1)
										pde.setStatus("Failed");
									else 
										pde.setStatus("Inducted");
									pde.setDrug(dde);//System.out.println(dde.getDrugname());
									break;
							default:
								//System.out.println("default is running");
							      break;
							  }
			cid++;
			}
			//System.out.println(list.size()+" excelhelper try block");
			if(f==0)
				list.add(pde);//System.out.println(list.size());
		}
	return list;
	}
}