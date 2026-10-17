package nihit.Nihit_yadav;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewTest {
	WebDriver driver;
  @Test
  public void openbrowser() {
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.get("https://www.google.com/");
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
