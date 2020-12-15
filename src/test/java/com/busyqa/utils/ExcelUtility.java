package com.busyqa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook wb = null;
	private XSSFSheet ws = null;
	private int rowNum=0;
	private int colNum=0;
	
	
	public ExcelUtility(String path)
	{
		this.path=path;
		File src = new File(path);
		try {
			fis = new FileInputStream(src);
			try {
				wb = new XSSFWorkbook(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String[][] getValidData() throws IOException {		
		ws = wb.getSheet("valid_creds");
		rowNum = ws.getLastRowNum() + 1; // Row num is index based
		colNum = ws.getRow(0).getLastCellNum(); // ALready have +1 in it
		String[][] credentials = new String[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				credentials[i][j] = ws.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return credentials;
	}

	@SuppressWarnings("deprecation")
	public String[][] getInvalidData() throws IOException {
		ws = wb.getSheet("invalid_creds");
		rowNum = ws.getLastRowNum() + 1; // Row num is index based
		colNum = ws.getRow(0).getLastCellNum(); // ALready have +1 in it
		String[][] credentials = new String[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if(ws.getRow(i).getCell(j).getCellType()==Cell.CELL_TYPE_BLANK)
				{
					credentials[i][j]="";
					System.out.print(credentials[i][j]);
					continue;
				}
				credentials[i][j] = ws.getRow(i).getCell(j).getStringCellValue();
			
			}
			
		}
		return credentials;
	}

}
