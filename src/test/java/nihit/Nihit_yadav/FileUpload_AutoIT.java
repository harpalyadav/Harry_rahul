package nihit.Nihit_yadav;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUpload_AutoIT {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String downloadpath= System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadpath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.ilovepdf.com/pdf_to_word");
		driver.findElement(By.xpath("//span[text()='Select PDF file']")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\HARSH\\Downloads\\FileUpload.exe");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("processTask")));
		driver.findElement(By.id("processTask")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickfiles")));
		driver.findElement(By.id("pickfiles")).click();
		Thread.sleep(2000);
		File file = new File(downloadpath + "\\Sample.docx");
		if (file.exists()) {
			System.out.println("File downloaded successfully");
		} else {
			System.out.println("File not downloaded");
		}
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("File not deleted");
		}
		

	}

}
