package nihit.Nihit_yadav;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExcel {
	
	@Test(dataProvider = "testData")
	public void testCaseData(String greeting, String communication, String id)
	{
		//System.out.println(greeting + " " + communication + " " + id);
	}
	DataFormatter Formatter = new DataFormatter();
	
	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\HARSH\\Downloads\\Mydata.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colCount =row.getLastCellNum();
		Object data[][] = new Object[rowCount-1][colCount];
		for(int i=0;i<rowCount-1;i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<colCount;j++)
			{
				XSSFCell cell = row.getCell(j);
				data[i][j]=Formatter.formatCellValue(cell);
		}
		}
		return data;
	
	}
}