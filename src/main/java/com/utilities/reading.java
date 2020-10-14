package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class reading {
static String value;
	
	public String read(String filename,String sheetName,int i, int j) throws IOException {
		
		//reading data from an excel sheet
		File f = new File("C:\\Users\\ADMIN\\eclipse-workspace\\Selenium_Project\\excel-files\\Data.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = null;
		
		String extension = filename.substring(filename.indexOf("."));
		
		//checking for extension xls
		if(extension.equals(".xls")) {
			
			wb=new HSSFWorkbook(fis);
			Sheet sh = wb.getSheet(sheetName);
			Cell cell = sh.getRow(i).getCell(j);
			
		    value = cell.getStringCellValue();
			
		}
		
		//checking for extension xlsx
		else if(extension.equals(".xlsx")) {
			
			wb=new XSSFWorkbook(fis);
			Sheet sh = wb.getSheet(sheetName);
			Cell cell = sh.getRow(i).getCell(j);
			
		    value = cell.getStringCellValue();
			
		}
		
			
			return value;
		
		
	}
}
