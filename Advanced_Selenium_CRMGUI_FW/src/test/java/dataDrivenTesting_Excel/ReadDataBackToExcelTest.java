package dataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBackToExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\Sneha\\Desktop\\Data\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(1);
		Cell cell = row.createCell(4);
		cell.setCellType(CellType.STRING);
//		cell.setCellValue("PASS");
		cell.setCellValue("FAIL");
		
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Sneha\\Desktop\\Data\\testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		System.out.println("====Executed====");

		
	}

}
