package nihit.Nihit_yadav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataBaseTesting {

	private static ResultSet ResultSet;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriver driver;
		String url = "jdbc:mysql://localhost:3306/selenium";
		String username = "root";
		String port = "root";
		driver = new ChromeDriver();
		//download and add mysql connector jar file in the project
		Connection cn = DriverManager.getConnection(url, username, port); 
		Statement st = cn.createStatement(); 
		ResultSet = st.executeQuery("select * from Employeeinfoneew where scenario='zerobalancecared'"); // add sql query for fetching data from database

		while (ResultSet.next()) {
			System.out.println(ResultSet.getString("username"));
			System.out.println(ResultSet.getString("password"));
			driver.get("https://www.facebook.com/login/");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(ResultSet.getString("username"));
			driver.findElement(By.xpath("//input[@name='pass']")).sendKeys(ResultSet.getString("password"));
		}

	}
}
