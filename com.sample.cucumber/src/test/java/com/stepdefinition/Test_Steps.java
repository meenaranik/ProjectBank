package com.stepdefinition;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {
	public static WebDriver driver = null;
	public static WebElement element = null;
	public static Select selElement = null;
	@Given("^user is on browser$")
	public void user_is_on_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
			
		driver = new ChromeDriver();

		//Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception

		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
	    
	}
	@When("^user navigates to demo url$")
	public void user_navigates_to_demo_url() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("http://toolsqa.wpengine.com/automation-practice-form/");
		System.out.println("x");
	}
	



	@When("^selects the country dropdown, chooses Europe option$")
	public void selects_the_country_dropdown_chooses_Europe_option() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    element = driver.findElement(By.id("continents"));
	    selElement = new Select(element);
	    selElement.selectByIndex(1);
	    
	    System.out.println(element.getText());
	    
	    //choosing optiond from scroll down menu
	    element = driver.findElement(By.id("selenium_commands"));
	    selElement = new Select(element);
	    selElement.selectByVisibleText("Browser Commands");
	    System.out.println("\n SELENIUM COMMANDS");
	    System.out.println(element.getText());
	    
	}
	
	@When("^the user clicks the button$")
	public void the_user_clicks_the_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.id("submit")).click();
	}

	@Then("^test script closes the browser$")
	public void test_script_closes_the_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  driver.quit();
	}

	// NEXT sCENARIO
			@Given("^User is on Home Page$")
			public void user_is_on_Home_Page() throws Throwable {
				System.setProperty("webdriver.gecko.driver","C:\\geckodriver-v0.19.1-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
			    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			    driver.get("http://www.store.demoqa.com");
			}
		 
			@When("^User Navigate to LogIn Page$")
			public void user_Navigate_to_LogIn_Page() throws Throwable {
				driver.findElement(By.xpath(".//*[@id='account']/a")).click();
			}
		 
			@When("^User enters \"(.*)\" and \"(.*)\"$")
			public void user_enters_UserName_and_Password(String username, String password) throws Throwable {
				driver.findElement(By.id("log")).sendKeys(username); 	 
			    driver.findElement(By.id("pwd")).sendKeys(password);
			    driver.findElement(By.id("login")).click();
			}
		 
			@Then("^Message displayed Login Successfully$")
			public void message_displayed_Login_Successfully() throws Throwable {
				System.out.println("Login Successfully");
			}
		 
			@When("^User LogOut from the Application$")
			public void user_LogOut_from_the_Application() throws Throwable {
				driver.findElement (By.xpath(".//*[@id='account_logout']/a")).click();
			}
		 
			@Then("^Message displayed LogOut Successfully$")
			public void message_displayed_LogOut_Successfully() throws Throwable {
				System.out.println("LogOut Successfully");
				driver.close();
			}
	
	
	
	
	

}
