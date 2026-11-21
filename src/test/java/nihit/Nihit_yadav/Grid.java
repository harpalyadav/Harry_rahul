package nihit.Nihit_yadav;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Grid {
	DesiredCapabilities cap = new DesiredCapabilities();
     //cap.setBrowserName("chrome");
    // cap.setPlatform(Platform.WINDOWS);
	WebDriver driver;
  @Test
  public void openbrowser() throws InterruptedException, IOException {
	  //WebDriverManager.chromedriver().setup();
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
	  //driver = new ChromeDriver();
	  ChromeOptions options = new ChromeOptions();
	    driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
	  driver.get("https://www.google.com/");
	  Thread.sleep(10000);
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
	  driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(10));
	  driver.getTitle();
	  driver.findElement(By.name("q")).sendKeys("Rahul Shetty Academy");
	  driver.findElement(By.name("q")).sendKeys("");
	  //driver.findElement(By.xpath("//h3[text()='Rahul Shetty Academy - YouTube']")).submit();
  }
}
