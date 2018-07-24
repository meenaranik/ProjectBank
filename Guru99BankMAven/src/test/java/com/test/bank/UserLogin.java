package com.test.bank;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.test.bean.*;
import com.test.pagefactory.Guru99Login;



/**
 * 
 * @author Meenarani
 * The Test Script 05:
 * Verify the Login Section 
 * The script uses TestNG
 * 
 */
public class UserLogin {
	
	public static WebDriver driver; 
	
	
	/*
	 * Separate Function to Launch FireFox Browser
	 */
	@BeforeTest
	
	public static  void launchBrowser() throws Exception
	{
		
		System.setProperty("webdriver.gecko.driver","C:\\Users\\meena\\eclipse-workspace\\STAInt\\src\\geckodriver.exe");
	    File pathBinary = new File(Util.FIREFOX_PATH);
	    FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
	    
	    //FirefoxProfile firefoxProfile = new FirefoxProfile();
		//driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
	    DesiredCapabilities desired = DesiredCapabilities.firefox();
	    FirefoxOptions options = new FirefoxOptions();
	    desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
	    desired.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
	    
	    driver = new FirefoxDriver(options);
	    driver.get(Util.URL_PATH);
	}
	
	
	
	
	@DataProvider(name = "Authentication")
	public static Object[][] ReadExcel() throws Exception
		{
		  
		 
		
		//return  new Object[][]  {{"mngr144670","zEtYmYs"}};
		return new Object[][] {{"mngr14467","zEtYmYs"},
								{"mngr144670","zEtYmY"},
								{"mngr14467","zEtYmY"},
								{"mngr144670","zEtYmYs"}
			
		
		};
			                     //{"mngr125391","AnypEg"},
			                    // {"mngr12539","AnypEgu"},
			                     //{"mngr12539","AnypEg"}} ;   

		}

	@DataProvider(name = "PasswordChange")
	public static Object[][] PasswordChangeData() throws Exception
	{
	  
	 
	
	return  new Object[][]  {{"mngr125391","12345","12345$"},
		                       {"mngr125391","12345$","12345$"},
								{"mngr125391","12345$","12345%"}};
		                     //{"mngr125391","AnypEg"},
		                    // {"mngr12539","AnypEgu"},
		                     //{"mngr12539","AnypEg"}} ;   

	}
	
	@DataProvider(name= "Account No")
	public static Object[][] accountNoDetails() throws Exception
	{
		return  new Object[][]  {{"40187","40188","1000","cash"},
								
								{"45788","45789","5000","cash"},
								{"40187","40187","50000","cash"}};
           
	}
	
	
	/**s
	 * Start Testing Test script 05 
	 * 1) Go to http://www.demo.guru99.com/V4/ 
	 * 2) Enter valid UserId 
	 * 3) Enter valid Password 
	 * 4) Click Login Expected
	 * result: Login successful home page shown Message shown Welcome
	 * <managerid>
	 * 
	 * @throws Exception
	 */
	@Test(dataProvider = "Authentication")
	public void userLoginTestCases(String userName , String password ) throws InterruptedException {
		// TODO Auto-generated method stub

	     
	    
	     String actualBoxtitle = null;
	     String actualTitle = null;
	     List<WebElement> userIDDisplay=null;	    
	       
	     driver.manage().timeouts()
			.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
	     			
					    
					    
				 try {
					    // To find Elements on webpage	 
					     WebElement element;
					     Guru99Login userlogin = new Guru99Login(driver);
					     userlogin.loginToGuru99(userName, password);
					 
									
					
						try{ 
						    
					       	Alert alt = driver.switchTo().alert();
							actualBoxtitle = alt.getText(); // get content of the Alter Message
							alt.accept();
							if (actualBoxtitle.contains(Util.EXPECT_ERROR)) { // Compare Error Text with Expected Error Value
							    System.out.println("Error Test case Passed"); 
							} else {
							    System.out.println("Error Test case  Failed");
							}
						}    
					    catch (NoAlertPresentException Ex){ 
					    	actualTitle = driver.getTitle();
							// On Successful login compare Actual Page Title with Expected Title
					    	
					    	
							if (actualTitle.contains(Util.EXPECTED_TITLE)) {
							    System.out.println("Test case Passed");
							} else {
							    System.out.println("Test case  Failed");
							}
							
								
				        } 
						 
						
						 Thread.sleep(2000);
						 
						
				 }
				 catch (InterruptedException e) 
				 {
								// TODO Auto-generated catch block
								e.printStackTrace();
				 }
			     
	    
			 
	
	}
	
	/*
	 * Checks the ID displayed on webpage is the user Id after successful login
	 */

	@Test (priority= 2)
	public void checkIDDisplay() throws Exception
	{
		Object[][] usernameSet = new Object[5][5];
		List<WebElement> userIDDisplay=null;
		userIDDisplay = driver.findElements(By.className("heading3"));
		//if(userIDDisplay.contains("userName"))
		 for(WebElement el : userIDDisplay) {
		       System.out.println(el.getText());
		      if(el.getText().contains("mngr144670"))
			      System.out.println("Test case Passed and contains id :" +el.getText());
		}
		 
		Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
		// usernameSet = Util.readExcel(Util.EXCEL_FILEPATH) ;
	
	}
	/*
	 * Test method to Change the password
	 * SM1,SM2,SM3 covered
	 */
	//@Test(dataProvider = "PasswordChange",priority = 3)
	public void changePassword(String userName,String oldPassword , String newPassword) throws Exception
	{
		driver.findElement(By.linkText("Change Password")).click();
		
		driver.findElement(By.name("oldpassword")).sendKeys(oldPassword);
		driver.findElement(By.name("newpassword")).sendKeys(newPassword);
		driver.findElement(By.name("confirmpassword")).sendKeys(newPassword);
		driver.findElement(By.name("sub")).click();
		Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
		

		try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			if (actualBoxtitle.contains(Util.EXPECT_ERROR2)|| actualBoxtitle.contains(Util.EXPECT_ERROR3)) 
			{ // Compare Error Text with Expected Error Value
			    System.out.println("Error Test case Passed"); 
			} else {
			    System.out.println("PasswordChanged");
			    driver.get(Util.URL_PATH);
			    userLoginTestCases(userName,newPassword);
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	// Get text displayes on login page 
	    	driver.findElement(By.linkText("Change Password")).click();
				
        } 
		
	}
	/*
	 * Test case to ADD New Customer
	 * 
	 */
	@Test(priority = 3)
	 //unable to enter date field from script so added customer & account manually
	public void addNewCustomer()throws Exception
	{
		
		NewCustomerPOM newCustomer = new NewCustomerPOM();
		newCustomer .setNewCustomerDriver(driver);
		newCustomer.addNewCustomer();
		
		try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
		
			if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
					actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)|| actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER4)
					||actualBoxtitle.endsWith(Util.EXPECT_ERROR2) )
			{ // Compare Error Text with Expected Error Value
			    
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	// Get text displayes on login page 
	    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png");
	    	driver.findElement(By.linkText("Continue")).click();
	    	System.out.println("Error Test case Passed"); 
	    	driver.navigate().refresh(); //for SM25 refresh page
	    	
	    	
        } 
		//Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 

		
	}
	
	
	@Test(priority = 4)
	//SM18
	public void miniStatement()throws Exception
	{
		driver.findElement(By.linkText("Mini Statement")).click();
		driver.findElement(By.name("accountno")).sendKeys("45788");
		
		
		driver.findElement(By.name("AccSubmit")).click();
		
		try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			System.out.println("Error Test case Passed"); 
			if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
					actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)) 
			{ // Compare Error Text with Expected Error Value
			    System.out.println("Error Test case Passed"); 
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	// Get text displayes on login page 
	    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
	    	//driver.navigate().refresh(); //for SM17 refresh page
	    	
	    	driver.findElement(By.linkText("Continue")).click();
				
        } 
		
	}
	
	
	@Test(priority = 5)
	//SM23
	//on Edit Customer page the text boxes are diabled so didn't allow to edit either manually or automatically
	public void editCustomer()throws Exception
	{
		driver.findElement(By.linkText("Edit Customer")).click();
		driver.findElement(By.name("cusid")).sendKeys("79705");
		
		
		driver.findElement(By.name("AccSubmit")).click();
		
		try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxtitle = alt.getText(); // get content of the Alter Message
			
			System.out.println(actualBoxtitle); 
			if (actualBoxtitle.contains(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
					actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)) 
			{ // Compare Error Text with Expected Error Value
			    System.out.println("Error Test case Passed alert"); 
			    Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
			}
			alt.accept();
		}    
	    catch (NoAlertPresentException Ex){ 
	    	// Get text displayes on login page 
	    	System.out.println(driver.findElement(By.name("dob")).isEnabled());
	    	driver.findElement(By.name("sub")).click();
	    	try{ 
			    
		       	Alert alt = driver.switchTo().alert();
				String actualBoxtitle = alt.getText(); // get content of the Alter Message
				alt.accept();
				System.out.println("Error Test case Passed1"); 
				if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
						actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)||actualBoxtitle.endsWith(Util.EXCEPT_ERROR_EDITCUSTOMER_MSG1)) 
				{ // Compare Error Text with Expected Error Value
				    System.out.println("Error Test case Passed2"); 
				}
			} 
	    	catch(NoAlertPresentException EX)
	    	{
	    		Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
		    	//driver.navigate().refresh(); //for SM17 refresh page
	    	}
	    	
	    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
	    	//driver.navigate().refresh(); //for SM17 refresh page
	    	
	    	
				
        } 
		
	}
	
	@Test(priority = 6,dataProvider = "Account No")
	//SM24 deposit Money
	public void depositMoney(String srcAccount,String destAccount,String amount,String desc)throws Exception
	{
		driver.findElement(By.linkText("Deposit")).click();
		driver.findElement(By.name("accountno")).sendKeys(srcAccount);
		
		driver.findElement(By.name("ammount")).sendKeys(amount);
		driver.findElement(By.name("desc")).sendKeys(desc);
		driver.findElement(By.name("AccSubmit")).click();
		
		try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			System.out.println("Error Test case Passed"); 
			if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
					actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)|| actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER4)
					||actualBoxtitle.endsWith(Util.EXPECT_ERROR2) )
			{ // Compare Error Text with Expected Error Value
			    System.out.println("Error Test case Passed"); 
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	// Get text displayes on login page 
	    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png");
	    	driver.findElement(By.linkText("Continue")).click();
			
	    	driver.navigate().refresh(); //for SM25 refresh page
	    	
	    	
        } 
		
	}
	
	
	
	@Test(priority = 7,dataProvider = "Account No")
	//SM24 deposit Money
	public void withdrawMoney(String srcAccount,String destAccount,String amount,String desc)throws Exception
	{
		driver.findElement(By.linkText("Withdrawal")).click();
		driver.findElement(By.name("accountno")).sendKeys(srcAccount);
		
		driver.findElement(By.name("ammount")).sendKeys(amount);
		driver.findElement(By.name("desc")).sendKeys(desc);
		driver.findElement(By.name("AccSubmit")).click();
		
		try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			String actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			System.out.println("Error Test case Passed"); 
			if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
					actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)|| actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER4)
					||actualBoxtitle.endsWith(Util.EXPECT_ERROR2) )
			{ // Compare Error Text with Expected Error Value
			    System.out.println("Error Test case Passed"); 
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	// Get text displayes on login page 
	    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png");
	    	driver.findElement(By.linkText("Continue")).click();
			
	    	driver.navigate().refresh(); //for SM25 refresh page
	    	
	   /* 	//*[@id="leftcontainer"]/table/thead/tr/th[1]
	    	//*[@id="leftcontainer"]/table/tbody
	    	//*[@id="leftcontainer"]/table/tbody/tr[1]/td[1] 
	    	 * //*[@id="leftcontainer"]/table/tbody/tr[3]/td[3]*/
	    	
        } 
	}
	
	@Test(priority = 8,dataProvider = "Account No")
		//SM19,SM16,SM21,SM22,SM17,SM20
		public void fundTransfer(String srcAccount,String destAccount,String amount,String desc)throws Exception
		{
			driver.findElement(By.linkText("Fund Transfer")).click();
			driver.findElement(By.name("payersaccount")).sendKeys(srcAccount);
			driver.findElement(By.name("payeeaccount")).sendKeys(destAccount);
			driver.findElement(By.name("ammount")).sendKeys(amount);
			driver.findElement(By.name("desc")).sendKeys(desc);
			driver.findElement(By.name("AccSubmit")).click();
			
			try{ 
			    
		       	Alert alt = driver.switchTo().alert();
				String actualBoxtitle = alt.getText(); // get content of the Alter Message
				alt.accept();
				//System.out.println("Error Test case Passed"); 
				if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
						actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)|| actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER4)) 
				{ // Compare Error Text with Expected Error Value
				   // System.out.println("Error Test case Passed"); 
				}
			}    
		    catch (NoAlertPresentException Ex){ 
		    	// Get text displayes on login page 
		    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png"); 
		    	//driver.navigate().refresh(); //for SM17 refresh page
		    	
		    	driver.findElement(By.linkText("Continue")).click();
					
	        } 
			
		}
		
		@Test(priority = 9,dataProvider = "Account No")
		//SM27 - SM30 deposit Money
		public void balanceEnquiry(String srcAccount,String destAccount,String amount,String desc)throws Exception
		{
			driver.findElement(By.linkText("Balance Enquiry")).click();
			driver.findElement(By.name("accountno")).sendKeys(srcAccount);
			
			
			
			driver.findElement(By.name("AccSubmit")).click();
			
			try{ 
			    
		       	Alert alt = driver.switchTo().alert();
				String actualBoxtitle = alt.getText(); // get content of the Alter Message
				alt.accept();
				System.out.println("Error Test case Passed"); 
				if (actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER)||actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER1)||
						actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER3)|| actualBoxtitle.endsWith(Util.EXPECT_ERROR_FUNDTRANSFER4)
						||actualBoxtitle.endsWith(Util.EXPECT_ERROR2) )
				{ // Compare Error Text with Expected Error Value
				    System.out.println("Error Test case Passed"); 
				}
			}    
		    catch (NoAlertPresentException Ex){ 
		    	// Get text displayes on login page 
		    	Util.takeSnapShot(driver, "C://Users//meena//eclipse-workspace//ProjectBankTestNG//Good Records.png");
		    	driver.findElement(By.linkText("Continue")).click();
				
		    	driver.navigate().refresh(); //for SM25 refresh page
		    	
		    	
	        } 
		
	}
	
	@AfterTest
	public void driverClose()
	{
		driver.quit();
	}
	
	
}
