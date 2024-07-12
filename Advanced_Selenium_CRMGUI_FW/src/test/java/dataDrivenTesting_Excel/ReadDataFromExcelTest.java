package dataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//1.get the Excel path location & java object of the physical excelFile
		FileInputStream fis = new FileInputStream("C:\\Users\\Sneha\\Desktop\\Data\\testScriptData.xlsx");

		//2.open workbook in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//3.get the control of the "org" sheet
		Sheet sh = wb.getSheet("org");
		
		//4.get the control of 1st row
		Row row = sh.getRow(1);
		
		//5.get the control of the 2nd cell and read the String data
//		faceBook
		Cell cell = row.getCell(2);
		String data1 = cell.getStringCellValue();
		System.out.println(data1);
		
//		createOrgTest
		String data2 = row.getCell(1).getStringCellValue();
		System.out.println(data2);
		
//		200
		String data3 = row.getCell(3).getStringCellValue();
		System.out.println(data3);
		
		//6.close the workbook
		wb.close();
	}

}
