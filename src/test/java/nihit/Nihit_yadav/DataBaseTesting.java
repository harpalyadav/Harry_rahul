package nihit.Nihit_yadav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Statement;

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
		driver.get("https://www.google.com/");
		Connection cn = DriverManager.getConnection(url, username, port);
		Statement st = cn.createStatement();
		ResultSet = st.executeQuery(""); // add sql query for fetching data from database

		while (ResultSet.next()) {
			System.out.println(ResultSet.getString("Username"));
			System.out.println(ResultSet.getString("Password"));
		}

	}
}
