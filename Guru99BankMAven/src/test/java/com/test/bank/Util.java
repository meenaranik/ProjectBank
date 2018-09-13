
/* Declare some common parameters for scripts
* You can change them to adapt your environment
*
*/


package com.test.bank;

import java.io.File;
import java.io.FileInputStream;


import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public  class Util {

	public static  final String FIREFOX_PATH = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	
	public static  final String URL_PATH ="http://www.demo.guru99.com/V4/";
	// Path to Source Excel file
	public static  final String EXCEL_FILEPATH = "C:\\Users\\meena\\excelforcourse\\userpwdbankinfo.xlsx";
	public static final String EXCEL_FILEPATH_NEWCUSTOMER = "C:\\Users\\meena\\excelforcourse\\NewCustomerInfo.xlsx";
	// Expected output
	public static final String EXPECTED_TITLE = "Guru99 Bank Manager HomePage"; 
	public static final String EXPECT_ERROR = "User or Password is not valid";
	public static final String EXPECT_ERROR2 = "Please fill all fields";
	public static final String EXPECT_ERROR3 = "Old Password is incorrect";
	public static final String EXPECT_ERROR_FUNDTRANSFER= "does not exist!!";
	public static final String EXPECT_ERROR_FUNDTRANSFER_CUSTOMER= "Customer does not exist!!";
	public static final String EXPECT_ERROR_FUNDTRANSFER1= "Balance low!!";
	public static final String EXPECT_ERROR_FUNDTRANSFER3= "Must Not Be Same!!!";
	public static final String EXPECT_ERROR_FUNDTRANSFER4= "Transfer Funds from this account!!";
	
	public static final String EXCEPT_ERROR_EDITCUSTOMER_MSG1 = "No Changes made to Customer records";
	
	// Time to wait when searching for a GUI element 
	public static final int WAIT_TIME = 30; 
	
	//Function to read Excel and sent the credentials
	
	public static Object[][] readExcel(String xlFilePath) 
	{
	  Object[][] credentials = new String[10][15]; 
	  try
	  {
		  DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		  Cell cell ;
		  
		File file =    new File(xlFilePath.trim());
		 FileInputStream inputStream = new FileInputStream(file);
		 XSSFWorkbook guru99Workbook = null;
		 guru99Workbook = new XSSFWorkbook(inputStream);
		 XSSFSheet guru99Sheet = guru99Workbook.getSheet("sheet1");
		 
		  int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();

		    //Create a loop over all the rows of excel file to read it

		    for (int i = 1; i < rowCount+1; i++) {

		        Row row = guru99Sheet.getRow(i);

		        //Create a loop to print cell values in a row

		        for (int j = 0; j <= row.getLastCellNum(); j++) {

		            //store excel data in 2d sting array
		        	cell = row.getCell(j);
		        	credentials[i-1][j]= formatter.formatCellValue(cell);
		        	
		            System.out.println(credentials[i-1][j]);

		        }
		        
		        
		    }
		    guru99Workbook.close();
	  }
	  catch (Exception e)
	  {
		   e.printStackTrace();
	  }
	
	return credentials;	    

	}
	
	/*
	 * Take screen shot of the webpage
	 * 
	 */
	
	
	
	 public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

	        //Call getScreenshotAs method to create image file

	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	            //Move image file to new destination

	                File DestFile=new File(fileWithPath);

	                //Copy file at destination

	                FileUtils.copyFile(SrcFile, DestFile);

	    }

}