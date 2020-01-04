/*Assignment test:
Create a project using selenium,Java to do the following :

1. Open  Page https://test.animana.com/web2/login
2. Login
3. Select the location Hilversum
4. Search for client Atopica
5. Assert the search result
6. Run on local chrome/Firefox browser

Tools to be used for Test: Maven,Selenium
*/
package Idexx;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class AnimanaTest {
	public static void main(String[] args) {
		// Create variables and assign values
		String url = "https://test.animana.com/web2/login";
		String username = "testnl40";
		String password = "Test#12345";
		String browserList[] = { "Chrome", "Firefox" }; // Create array
		// To store Firefox or Chrome object
		WebDriver driver;

		// Create a loop to run the test on different browsers from browserList
		for (String x : browserList) {

			if (x == "Chrome") {
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
				WebDriver Chrome_driver = new ChromeDriver();
				driver = Chrome_driver;
			} else
			// Firefox
			{
				System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
				WebDriver Fx_driver = new FirefoxDriver();
				driver = Fx_driver;
			}
			// Launch Firefox or Chrome and direct it to the URL
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			// Find the WebElement for username
			WebElement Username = driver.findElement(By.id("username"));
			// Find the WebElement for password
			WebElement Password = driver.findElement(By.id("password"));
			// Find the WebElement for Login button
			WebElement login = driver.findElement(By.id("logon-login-button"));
			// Enter values into username and password fields then click the button
			Username.sendKeys(username);
			Password.sendKeys(password);
			login.click();
			// Find and select the location Hilversum
			WebElement Hilversum = driver.findElement(By.xpath("//li[5]//input[1]"));
			Hilversum.click();
			// Switch to iframe
			driver.switchTo().frame("mana");
			// Find client search field and enter value Atopica
			WebElement ClientSearch = driver.findElement(By.xpath("//input[@id='headerSearchInput']"));
			ClientSearch.sendKeys("Atopica");
			WebElement ClientBtn = driver.findElement(By.xpath("//button[@id='mainSearchButton']"));
			// Click client search button
			ClientBtn.click();
			// Find search results table
			WebElement mytable = driver.findElement(By.xpath("//table[@id='findResult-searchResult-table']//tbody"));
			// To locate rows in Clients table.
			List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
			// If number of rows in search result table is greater than 0
			if (rows_table.size() > 0) {
				System.out.println("Client Atopica is present.");
			} else {
				System.out.println("Client Atopica is not found.");
			}
			driver.quit();
		}

	}
}
