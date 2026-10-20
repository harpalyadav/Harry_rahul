package nihit.Nihit_yadav;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;



public class DataDriven_Testing {
	public ArrayList<String> getData(String testcaseName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();

		// FileInputStream fis = new
		// FileInputStream("C:\\Users\\HARSH\\Downloads\\Mydata.xlsx");
		FileInputStream fis = new FileInputStream("C:\\Users\\HARSH\\Downloads\\Mydata.xlsx");
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			int sheets = workbook.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
					XSSFSheet sheet = workbook.getSheetAt(i);
					// Identify the testcases coloum by scanning the entire 1st row
					Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
					Row firstrow = rows.next();
					Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cells
					int K = 0;
					int Column = 0;
					while (ce.hasNext()) {
						Cell value = ce.next();
						if (value.getStringCellValue().equalsIgnoreCase("Testcases")) {
							// desired column

							Column = K;
						}
						K++;
					}
					System.out.println(Column);
					// once column is identified then scan entire testcase column to identify
					// purchase testcase row
					while (rows.hasNext()) {
						Row r = rows.next();
						if (r.getCell(Column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
							// after you grab purchase testcase row = pull all the data of that row and feed
							// into test
							Iterator<Cell> cv = r.cellIterator();
							while (cv.hasNext()) {
								Cell c = cv.next();
								if (c.getCellType() == CellType.STRING)
								{
									a.add(c.getStringCellValue());
								} else {
									a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								}
							}
						}
					}
				}
			}
		}
		return a;
	}

	public static void main(String[] args) throws IOException {
	    DataDriven_Testing d = new DataDriven_Testing();
	    ArrayList<String> data = d.getData("Purchase");
	    System.out.println("Size = " + data.size());
	    System.out.println(data);
	}

	}


