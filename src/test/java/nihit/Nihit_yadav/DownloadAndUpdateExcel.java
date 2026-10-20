package nihit.Nihit_yadav;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DownloadAndUpdateExcel {

	public static void main(String[] args) throws IOException {

		String fruitName = "Apple";
		String updatedValue = "600";

		// Chrome saves the downloaded file in your Downloads folder by default
		String filePath = "C:\\Users\\HARSH\\Downloads\\download.xlsx";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");

		// download
		driver.findElement(By.cssSelector("#downloadButton")).click();

		// edit excel - getColumnNumber, getRowNumber, updateCell
		int col = getColumnNumber(filePath, "price");
		int row = getRowNumber(filePath, fruitName);
		Assert.assertTrue(updateCell(filePath, row, col, updatedValue));

		// upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys(filePath);

		// wait for success message to show up and wait for disappear
		By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		String toastText = driver.findElement(toastLocator).getText();
		System.out.println(toastText);
		Assert.assertEquals("Updated Excel Data Successfully.", toastText);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));

		// verify updated excel data showing in the web table
		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
		System.out.println(actualPrice);
		Assert.assertEquals(updatedValue, actualPrice);

		driver.quit();
	}

	private static boolean updateCell(String filePath, int row, int col, String updatedValue) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowField = sheet.getRow(row - 1);
		Cell cellField = rowField.getCell(col - 1);
		cellField.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;
	}

	private static int getColumnNumber(String filePath, String colName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> ce = firstRow.cellIterator();
		int k = 1;
		int column = 0;
		while (ce.hasNext()) {
			Cell value = ce.next();
			if (value.getStringCellValue().equalsIgnoreCase(colName)) {
				column = k;
			}
			k++;
		}
		workbook.close();
		fis.close();
		return column;
	}

	private static int getRowNumber(String filePath, String text) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		Iterator<Row> rows = sheet.iterator();
		int k = 1;
		int rowIndex = -1;
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(text)) {
					rowIndex = k;
				}
			}
			k++;
		}
		workbook.close();
		fis.close();
		return rowIndex;
	}
}