package com.test.bean;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.test.bank.Util;

public class NewCustomerPOM {
	 
	 WebDriver driver;
	  Object[][] rowFromCustomerInfo = 	 Util.readExcel(Util.EXCEL_FILEPATH_NEWCUSTOMER);
	  static int rowCount = 0;
     public  void setNewCustomerDriver(WebDriver driver)
     {
    	 this.driver = driver;
     }
	 public   void setCustomerName()
	   {
	      driver.findElement(By.name("name")).sendKeys((String)rowFromCustomerInfo[rowCount][1]);;
	     
	   }
	
	 
	 public void setMaleRadio()
	   {
	       driver.findElement(By.name("rad1")).click();
	      
	   }
	 public  void setFemaleRadio()
	   {
	     driver.findElement(By.name("rad2")).click();
	     
	   }
	 public  void setDob()
	   {
	      
	       driver.findElement(By.name("dob")).sendKeys("25/09/2013");
	      
	   }
	 public  void setAddress()
	   {
	      driver.findElement(By.name("addr")).sendKeys((String)rowFromCustomerInfo[rowCount][4]);;
	     
	   }
	 public  void setCity()
	   {
	      driver.findElement(By.name("city")).sendKeys((String)rowFromCustomerInfo[rowCount][5]);;
	     
	   }
	 public  void setState()
	   {
	      driver.findElement(By.name("state")).sendKeys((String)rowFromCustomerInfo[rowCount][6]);;
	  
	   }
	 public  void setPin()
	   {
	    driver.findElement(By.name("pinno")).sendKeys((String)rowFromCustomerInfo[rowCount][7]);
	     
	   }
	 public  void setMobileno()
	   {
	       driver.findElement(By.name("telephoneno")).sendKeys((String)rowFromCustomerInfo[rowCount][8]);
	      
	   }
	 public  void setEmail()
	   {
	       driver.findElement(By.name("emailid")).sendKeys((String)rowFromCustomerInfo[rowCount][9]);;
	     
	   }
	 public  void setPwd()
	   {
	     driver.findElement(By.name("password")).sendKeys((String)rowFromCustomerInfo[rowCount][10]);;
	      
	   }
	 public  void clickSubmitbtn()
	   {
	     driver.findElement(By.name("sub")).click();
	      
	   }
	 
 
	 
	 
	 
	 public  void addNewCustomer()
	 {
			driver.findElement(By.linkText("New Customer")).click();
			setCustomerName();
			
			 if(rowFromCustomerInfo[rowCount][2].toString().equalsIgnoreCase("male"))
				{
					setMaleRadio();
					
				}
				else
				{
				setFemaleRadio();
		 
			}
			 setDob();
			 setAddress();
			 setCity();
			 setState();
			 setPin();
			 setMobileno();
			 setEmail();
			 setPwd();
			 clickSubmitbtn();
			 rowCount++;
	 
	 }
	 
	 
	 
	 
}