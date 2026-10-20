package nihit.Nihit_yadav;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvide {
	
	@Test(dataProvider = "testData")
	public void testCaseData(String greeting, String communication, int id)
	{
		System.out.println(greeting + " " + communication + " " + id);
	}
	
	@DataProvider(name = "testData")
	public Object[][] getData()
	{
		Object[][] data = {
			{"Hello", "How are you?", 1},
			{"Hi", "What is your name?", 2},
			{"Hey", "Where are you from?", 3}
		};
		return data;
	}
}