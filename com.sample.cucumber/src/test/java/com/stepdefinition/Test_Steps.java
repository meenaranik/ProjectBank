package com.stepdefinition;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test_Steps {
	public static WebDriver driver = null;
	@Given("^user is on browser$")
	public void user_is_on_browser() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
			
		driver = new ChromeDriver();

		//Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception

		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
	    
	}

	@When("^user navigates to youtube url$")
	public void user_navigates_to_youtube_url() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		
		driver.get("https://www.youtube.com");
		System.out.println("x");
		
	    driver.findElement(By.id("search")).sendKeys("Remo");
	    driver.findElement(By.id("search-icon-legacy")).click();
	    
	}

	@Then("^will be able to see the website$")
	public void will_be_able_to_see_the_website() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		driver.quit();
	}

}
