import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class AspireTest extends Locators
{

	Locators lo = new Locators();
	WebDriver driver;
	String url= "https://aspireapp.odoo.com/";
	String Account= "user@aspireapp.com";
	String Password = "@sp1r3app";
	public static String ProdName;

	

	//@Test
	public void Exercise_1() throws InterruptedException
	{
//		WebDriverManager.firefoxdriver().setup();
//		driver= new FirefoxDriver();
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
		WebDriverWait wait = new WebDriverWait(driver, 100); 

		//1. Login to web application
		launch_URL();
		Thread.sleep(5000);
		//2. Navigate to `Inventory` feature
		driver.findElement(By.xpath(lo.InventoryIcon)).click();

		//3. From the top-menu bar, select `Products -> Products` item, then create a new product
		Random ran = new Random();
		int rn = ran.nextInt((1000 - 1) + 1) + 1;
		Thread.sleep(5000);
		driver.findElement(By.xpath(lo.ProductsTab)).click();
		driver.findElement(By.xpath(lo.ProductsOption)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.CreateProductButton))).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath(lo.ProductName)).sendKeys("Auto Test "+rn);
		driver.findElement(By.xpath(lo.ProductSave)).click();
		ProdName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lo.ProdNameStr))).getText();
		System.out.println(ProdName);


		//4. Update the quantity of new product is more than 10
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.UpdateQuantity))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.CreateButon))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.QuantField))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.QuantField))).sendKeys("95651165");

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.LocField))).sendKeys("Virtual");
		WebElement el = driver.findElement(By.xpath(lo.DropDwnloc));
		Actions action = new Actions(driver);
		action.moveToElement(el).click().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.VirtData))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.ProductSave))).click();
		
		Thread.sleep(5000);
	}

		
		

		@Test
		public void Exercise_2() throws InterruptedException
		{   Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 100); 
			launch_URL();// to launch url
		//5. From top-left page, click on `Application` icon

		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.AppIcon))).click();
		//Thread.sleep(5000);
		
        //6. Navigate to `Manufacturing` feature, then create a new Manufacturing Order item
		//for the created Product on step #3
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lo.ManufacturingIcon))).click();
		//driver.findElement(By.xpath(lo.ManufacturingIcon)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.CreateButton))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.ManuProdName))).sendKeys("Auto Test ");//ProdName
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.SearchedResult))).click();
		
		driver.findElement(By.xpath(lo.AddALine)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lo.ProdUOMquant))).clear();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lo.ProdUOMquant))).sendKeys("111");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.ProdLine))).sendKeys("Auto Test");//ProdName
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.ProdLinSearchRes))).click();



				
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.ManuSave))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.ConfirmButton))).click();
		
		//7. Update the status of new Orders to “Done” successfully
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(lo.MarkDone))).click();
		
		//8. Validate the new Manufacturing Order is created with corrected information.
		if (driver.findElement(By.xpath(lo.ManRef)).isDisplayed()==true &&
			driver.findElement(By.xpath(lo.prodName)).isDisplayed()==true	) 
		{
			System.out.println("Manufacturing order is created : ");
			System.out.println("Manufacturing order : "+ driver.findElement(By.xpath(lo.ManRef)).getText());
			System.out.println("Product Name : "+ driver.findElement(By.xpath(lo.prodName)).getText());
		}
		else 
			System.out.println("Someting is wrong ...!!");
		
		Thread.sleep(10000);
		
		




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
		
	}
	
	@BeforeSuite
	public void beforeSuite()
	{
		WebDriverManager.firefoxdriver().setup();
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@AfterSuite
	public void afterSuite()
	{
		driver.quit();
	}
	
	public void launch_URL()
	{
		driver.get(url);
		driver.findElement(By.xpath(lo.login)).sendKeys(Account);
		driver.findElement(By.xpath(lo.password)).sendKeys(Password);
		driver.findElement(By.xpath(lo.LoginSubmit)).click();
	}


}