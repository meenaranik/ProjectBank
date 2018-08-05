
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class DropDown
{
   public static void main(String[] args) throws InterruptedException {
   
     // WebDriver driver = new FirefoxDriver();
      
      System.setProperty("webdriver.gecko.driver","C:\\Users\\meena\\eclipse-workspace\\STAInt\\src\\geckodriver.exe");
      File pathBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
      FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
      DesiredCapabilities desired = DesiredCapabilities.firefox();
      FirefoxOptions options = new FirefoxOptions();
      desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
      WebDriver driver = new FirefoxDriver(options);
      driver.get("https://www.google.co.in/");
      
      //Puts a Implicit wait, Will wait for 10 seconds before throwing exception
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      
      //Launch website
      driver.navigate().to("http://afritelly.com/Test_Eng/userint");
	  Thread.sleep(2000);
	    // Maximise the browser
      driver.manage().window().maximize();
      
      //Selecting an item from Drop Down list Box
      Select dropdown = new Select(driver.findElement(By.id("Combobox1")));
      dropdown.selectByVisibleText("Contact Request");
      
      //We can also use dropdown.selectByIndex(1) to select second element as index starts with 0.
      //We can also use dropdown.selectByValue("#############");
      
	  // Print a Log In message to the screen
      System.out.println("The Output of the IsEnabled " + driver.findElement(By.id("Combobox1")).isEnabled());
      System.out.println("The Output of the IsDisplayed " + driver.findElement(By.id("Combobox1")).isDisplayed());
	  Thread.sleep(5000);
      driver.close();
	  driver.quit();
   }
}
