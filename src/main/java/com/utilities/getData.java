package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData {

		static String email;
		static String pwd;
	public static ArrayList<Object[]> fetchData(String filename,String sheetName) throws IOException{
		//reading data from an excel sheet
				File f = new File("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\excel-files\\NegativeTest.xlsx");
				FileInputStream fis = new FileInputStream(f);
				Workbook wb = null;
				ArrayList<Object[]> myData=new ArrayList<Object[]>();
				String extension = filename.substring(filename.indexOf("."));
				
				//checking for extension xls
				if(extension.equals(".xls")) {
					
					wb=new HSSFWorkbook(fis);
					Sheet sh = wb.getSheet(sheetName);
					Cell cell = sh.getRow(1).getCell(0);
					email = cell.getStringCellValue();
					Cell cell1 = sh.getRow(1).getCell(1);
				    pwd = cell1.getStringCellValue();
					
				}
				
				//checking for extension xlsx
				else if(extension.equals(".xlsx")) {
					
					wb=new XSSFWorkbook(fis);
					Sheet sh = wb.getSheet(sheetName);
					Cell cell = sh.getRow(1).getCell(0);
					email = cell.getStringCellValue();
					Cell cell1 = sh.getRow(1).getCell(1);
				    pwd = cell1.getStringCellValue();
				    
				}
				Object ob[] = {email,pwd};
				myData.add(ob);
				return myData;
	}
}
