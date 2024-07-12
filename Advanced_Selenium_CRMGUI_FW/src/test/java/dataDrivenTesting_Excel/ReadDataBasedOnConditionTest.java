package dataDrivenTesting_Excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataBasedOnConditionTest {

	public static void main(String[] args) throws Throwable {
//		String expectedTestId = "tc_02";
		String expectedTestId = "tc_100";
		String data1 ="";
		String data2 ="";
		String data3 ="";
		boolean flag = false;


		FileInputStream fis = new FileInputStream("C:\\Users\\Sneha\\Desktop\\Data\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("org");

		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			String data = "";
			try {
				data = sh.getRow(i).getCell(0).toString();
				if(data.equals(expectedTestId))
				{
					flag=true;
					data1= sh.getRow(i).getCell(1).toString();
					data2= sh.getRow(i).getCell(2).toString();
					data3= sh.getRow(i).getCell(3).toString();
				}
			}
			catch (Exception e) {
//				System.out.println("handled");
			}
		}
		if(flag)
		{
		System.out.println(data1);
		System.out.println(data2);
		System.out.println(data3);
		}
		else
		{
			System.out.println(expectedTestId + "Data is not available");
		}

		wb.close();

	}
	}


