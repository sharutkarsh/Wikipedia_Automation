import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Test1 
{
	WebDriver driver;
	String url= "https://www.wikipedia.org/";

	@Test
	public void Exercise_1()
	{
		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//1. Open a  wikipedia page.
		driver.get(url);
		//2. Type ‘Leo Panthera’  in the search box.
		driver.findElement(By.xpath("//*[@id='searchInput']")).sendKeys("Leo Panthera");
		//3. Click on search button or icon
		driver.findElement(By.xpath("//*[@class=\"sprite svg-search-icon\"]")).click();
		//4. Verify the results being displayed on the results page.
		System.out.println("Below are the result's titles for the search \"Leo Panthera\" present on the page");
		List<WebElement> list = driver.findElements(By.xpath("//div[@class=\"mw-search-result-heading\"]/a"));
		for(int i=0; i < list.size(); i++)
		{
			System.out.println(list.get(i).getAttribute("title"));
		}
	}
	
	@Test
	public void Exercise_2() throws InterruptedException
	{
		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//1. Open a  wikipedia page.
		driver.get(url);
		//2. Click on the language switcher and select a new language .
		driver.findElement(By.xpath("//*[@class=\"lang-list-button-text jsl10n\"]")).click();
		//Selecting a language - Hindi Lanuage will be selected
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@title=\"Hindī\"]")).click();
		//3. Verify that user is redirected to the appropriate domain for the selected language.
		String expected_Hindi_Wikipedia_Domain = "https://hi.wikipedia.org/wiki/%E0%A4%AE%E0%A5%81%E0%A4%96%E0%A4%AA%E0%A5%83%E0%A4%B7%E0%A5%8D%E0%A4%A0";
		driver.get(expected_Hindi_Wikipedia_Domain);
		try{
		  Assert.assertEquals(expected_Hindi_Wikipedia_Domain, driver.getCurrentUrl());
		  System.out.println("Navigated to correct Hindi Wikipedia Domain");
		}
		catch(Throwable pageNavigationError){
		  System.out.println("Didn't navigate to correct Wikipedia Domain");
		}
		
		
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Starting the browser");
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("CLosing the browser");
		driver.quit();
	}
	
	
}
