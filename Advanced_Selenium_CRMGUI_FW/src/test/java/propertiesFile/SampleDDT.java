package propertiesFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SampleDDT{

	public static void main(String[] args) throws IOException {
		//step 1: get the java representation object of the physical file
		FileInputStream fis= new FileInputStream("C:\\Users\\Sneha\\Desktop\\commondata.properties");
		
		//step 2 : using properties class, load all keys
		Properties pObj = new Properties();
		pObj.load(fis);
		
		//step 3 : get the value based on key
		System.out.println(pObj.getProperty("url"));

	}

}
