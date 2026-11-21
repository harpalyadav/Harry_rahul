package nihit.Nihit_yadav;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload_AutoIT {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
		  WebDriver driver = new ChromeDriver();
		  driver.get("https://www.ilovepdf.com/pdf_to_word");
		  driver.findElement(By.xpath("//span[text()='Select PDF file']")).click();
		  Thread.sleep(2000);
		  Runtime.getRuntime().exec("C:\\Users\\HARSH\\Downloads\\FileUpload.exe");

	}

}
