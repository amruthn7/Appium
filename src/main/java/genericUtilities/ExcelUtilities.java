package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This is the class used to handle the Excel sheet
 * @author Amruth N
 *
 */
public class ExcelUtilities {
	DataFormatter df;
	Workbook wb;

	/**
	 * This constructor is used to create object for class
	 */
	public ExcelUtilities() {}

	/**
	 * This is the constructor with filePath as an argument
	 * @param filePath
	 */
	public ExcelUtilities(String filePath) {
		initialize(filePath);
	}

	/**
	 * This method used to load and initialize the excel sheet
	 * @param filePath
	 */
	private void initialize(String filePath) {
		df= new DataFormatter();
		FileInputStream fisExcel = null;
		try {
			fisExcel = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(fisExcel);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fisExcel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to fetch the data from excel sheet using TestScript Name,  
	 * key and sheet name as an arguments
	 * @param expTestScriptName
	 * @param expKey
	 * @param sheetName
	 * @return
	 */
	public String getData(String expTestScriptName, String expKey, String sheetName) {
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		String value="";
		int testScriptCounter=0;
		int keyCounter=0;
		for (int i = 1; i <= rowCount; i++) {
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			if(testScriptName.equalsIgnoreCase(expTestScriptName)) {
				testScriptCounter++;
				short cellCount = sheet.getRow(i).getLastCellNum();
				for (int j = 1; j < cellCount; j++) {    
					String key= df.formatCellValue(sheet.getRow(i).getCell(j));
					if(key.equalsIgnoreCase(expKey)) {
						keyCounter++;
						value = df.formatCellValue(sheet.getRow(i+1).getCell(j));
						break;
					}
				}
				break;
			}
		}
		if(keyCounter==0) {
			if(testScriptCounter==0) {
				value =("please give proper testscript key"+expTestScriptName);
			}
			else {
				value =("please give proper testscript key"+expTestScriptName);
			}
		}
		System.out.println("value fected from excel ===> " +value);
		return value;
	}


	/**
	 * This method is used to fetch the multiple data from excel sheet using TestScript Name, 
	 *  and Sheet name as an arguments
	 * @param expectedTestScriptName
	 * @param sheetName
	 * @return
	 */
	public Map<String, String> getData( String expectedTestScriptName, String sheetName) {
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		Map<String, String> map=new HashMap<String, String>();
		for (int i = 1; i <= rowCount; i++) {
			String testScriptName = df.formatCellValue(sheet.getRow(i).getCell(0));
			if(testScriptName.equalsIgnoreCase(expectedTestScriptName)) {
				short cellCount = sheet.getRow(i).getLastCellNum();
				for (int j = 1; j < cellCount; j++) {    
					String key = df.formatCellValue(sheet.getRow(i).getCell(j));
					String value = df.formatCellValue(sheet.getRow(i+1).getCell(j));
					if(key.equals(""))
						break;
					map.put(key, value);
				}
				break;
			}
		}
		return map;
	}
	
	/**
	 * This method is used to fetch the multiple data from excel sheet using 
	 * Sheet name as an arguments
	 * @param sheetName
	 * @return
	 */
	public String[][] getData(String sheetName) {
		Sheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		String [][] arr= new String [rowCount][sheet.getRow(0).getLastCellNum()];
		for(int i=1; i<=rowCount; i++) {
			for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
				arr[i-1][j]=df.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}
		return arr;

	}

	/**
	 * This method is used to fetch the data from excel sheet using Sheet name 
	 * and index of row and cell as an arguments
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	public String getData(String sheetName, int rowIndex, int cellIndex) {
		String value = df.formatCellValue(wb.getSheet(sheetName).getRow(rowIndex).createCell(cellIndex));
		return value;
	}


	/**
	 * This method used to close the Excel sheet
	 */
	public void close() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to write the data to excel sheet using Sheet name  
	 * and index of row and cell as an arguments
	 * @param sheetName
	 * @param rowIndex
	 * @param cellIndex
	 */
	public void setData(String sheetName, int rowIndex, int cellIndex) {
		wb.getSheet(sheetName).getRow(rowIndex).createCell(cellIndex);
	}

	
	/**
	 * This method is used to save the Excel sheet
	 * @param fileOutputPath
	 */
	public void saveData(String fileOutputPath) {
		try {
			wb.write(new FileOutputStream(fileOutputPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
