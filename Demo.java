import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.Properties;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Demo extends DataResources
{
	DataResources dat = new DataResources();
	WebDriver driver;
	
	String url="https://the-internet.herokuapp.com/";
	
	@Test
	public void Exercise_1() throws Exception
	{

		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(url);
		//1. Click on ‘Frames’ hyperlink available in the page 
		driver.findElement(By.xpath(dat.frames)).click();
		System.out.println("Clicked on frame");
		
		//2. Check if the new page header has ‘Frames’ wording
        String actualString = driver.findElement(By.xpath(dat.FrameHeader)).getText();
        assertTrue(actualString.contains("Frames"));
        System.out.println("Checked frames wording");
        
        //3. Click on iFrame link & check if we navigated to next page
        driver.findElement(By.xpath(dat.iFrameStr)).click();
        String iFrameurl = "https://the-internet.herokuapp.com/iframe";
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(iFrameurl, strUrl);
        System.out.println("Clicked on iframe link");
        
        //4. Check if the page header has text ‘TinyMCE’ in it
        String actualStr1 = driver.findElement(By.xpath(dat.iFrameHeader)).getText();
        assertTrue(actualStr1.contains("TinyMCE"));
        System.out.println("Checked for TinyMCE");
        
        //5. Click on the text field and remove ‘Your content goes here.’ text
        Thread.sleep(5000);
        driver.switchTo().frame("mce_0_ifr");
        WebElement element = driver.findElement(By.xpath(dat.field1));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.getElementByXpath('//body[@id='tinymce']/p').value='';");
        executor.executeScript("arguments[0].click();", element);
//        driver.findElement(By.xpath(dat.field1)).click();
//        driver.findElement(By.xpath(dat.field1)).clear();
        System.out.println("Cleared Text Field");
        
        //6. Check if the page has parent menu items as File, Edit, View, Format
        if(driver.findElements(By.xpath(dat.File)).size() != 0 &&
           driver.findElements(By.xpath(dat.Edit)).size() != 0	&&
          	driver.findElements(By.xpath(dat.View)).size() != 0  &&
         	driver.findElements(By.xpath(dat.Format)).size() != 0)
        {
        	System.out.println("Element page has parent menu items as File, Edit, View, Format - Present");
        	}else{
        	System.out.println("Element is Absent");
        	}
        
        
        //Enter text as ‘Test’ & select the entire text, go to Format – Formats – Headings – Heading 1
        Thread.sleep(5000);
        driver.findElement(By.xpath(dat.field1)).sendKeys("Test");
        String SelectedText = driver.findElement(By.xpath(dat.field1)).getText();
        System.out.println("Entered Text test");
        
        driver.findElement(By.xpath(dat.Format)).click();
        driver.findElement(By.xpath(dat.Formats)).click();
        driver.findElement(By.xpath(dat.Headings)).click();
        driver.findElement(By.xpath(dat.Heading1)).click();
        
        //8. Click on Elemental Selenium link at the bottom of the page
        String oldTab = driver.getWindowHandle();
        driver.findElement(By.xpath(dat.ElementalSelenium)).click();
        System.out.println("Click on Elemental Selenium link at the bottom of the page");
        
        //9. Check if the user is taken to new tab in the same browser with link -http://elementalselenium.com/
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        // change focus to new tab
        System.out.println("The new tab url is "+driver.getCurrentUrl());
        System.out.println("user is taken to new tab");
        
        //10. Navigate back to https://the-internet.herokuapp.com/iframe tab & close the tab with http://elementalselenium.com/
        Thread.sleep(5000);
        driver.close();
        driver.switchTo().window(tabs2.get(0));
        System.out.println("user is back to old tab");
        
        
       
	}	// end of test case
	
	@BeforeMethod
	public void beforeMethod()
	{
		System.out.println("Starting the browser");
	}
	
	@AfterMethod
	public void afterMethod()
	{
		System.out.println("Closing the browser");
		driver.quit();
	}

}
