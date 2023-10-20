package Greenmouse.Pikaboo.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import Greenmouse.Pikaboo.resources.ExtentReporterNG;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest extends ExtentReporterNG {

	public WebDriver driver;

	

	public WebDriver initializeDriver() throws IOException

	{
		// properties class

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Greenmouse//Pikaboo//resources//GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable notifications");
			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(cp);

			WebDriverManager.chromedriver().setup();
			

			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));// full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "//Users//user//Desktop//Selenium//geckodriver");
			driver = new FirefoxDriver();
			// Firefox
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	
	// Utility to get screenshot
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
		{
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
			
		}


		@AfterMethod(alwaysRun = true)
		public void tearDown() {
			driver.close();
		}
	
	
	
	

	public void adminLauch() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://admin.mypikaboo.com/auth/admin");
	
	}
	
	public void adminLogin() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		
		driver.findElement(By.id("email")).sendKeys("admin@mypikaboo.com");
		driver.findElement(By.id("password")).sendKeys("Password");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Login']")).click();	
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Login']")));
	}
	
	public void adminAddFleetManager() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//span[text()='Staffs']")).click();
		driver.findElement(By.xpath("//span[text()='Fleet Managers']")).click();
		driver.findElement(By.xpath("//p[text()='Add New Fleet Manager']")).click();
		driver.findElement(By.id("first_name")).sendKeys("QA Daniel");
		driver.findElement(By.id("last_name")).sendKeys("Fleet");
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+fleet@gmail.com");
		
		WebElement options = driver.findElement(By.xpath("//select[@name='gender']"));
		Select option= new Select(options);
		option.selectByVisibleText("Male");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.id("password_confirmation")).sendKeys("Dandytech@2022");
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Create Fleet Manager']")).click();
		

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		
		
	}
	
	public void adminAddWasteMgZone() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Staffs']")));
		
		driver.findElement(By.xpath("//span[text()='Staffs']")).click();
		driver.findElement(By.xpath("//span[text()='Waste Managers']")).click();
		
		driver.findElement(By.xpath("//p[text()='Waste Manager Zone']")).click();
		driver.findElement(By.xpath("//p[text()='Add New Zone Manager']")).click();
		
		WebElement zones = driver.findElement(By.xpath("//select[@name='zone_id']"));
		Select zone= new Select(zones);
		zone.selectByVisibleText("Zone A");
		
		driver.findElement(By.id("first_name")).sendKeys("QA Daniel");
		driver.findElement(By.id("last_name")).sendKeys("Zone");
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+wmz@gmail.com");
		
		WebElement options = driver.findElement(By.xpath("//select[@name='gender']"));
		Select option= new Select(options);
		option.selectByVisibleText("Male");
		
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.id("password_confirmation")).sendKeys("Dandytech@2022");
	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Create Waste Manager']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));	
		
	}
	
	
	public void adminAddWasteMgTruck() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Staffs']")));
		
		driver.findElement(By.xpath("//span[text()='Staffs']")).click();
		driver.findElement(By.xpath("//span[text()='Waste Managers']")).click();
		
		driver.findElement(By.xpath("//p[text()='Waste Manager Truck']")).click();
		driver.findElement(By.xpath("//p[text()='Add New Truck Manager']")).click();
		
		driver.findElement(By.id("first_name")).sendKeys("QA Daniel");
		driver.findElement(By.id("last_name")).sendKeys("Truck");
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+wmt@gmail.com");
		
		WebElement options = driver.findElement(By.xpath("//select[@name='gender']"));
		Select option= new Select(options);
		option.selectByVisibleText("Male");
		
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.id("password_confirmation")).sendKeys("Dandytech@2022");
	
		driver.findElement(By.xpath("//button[text()='Create Waste Manager']")).click();
		
		Thread.sleep(10000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		
		
	}
	
	
	
	public void adminAddFieldOperator() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Staffs']")));
		
		driver.findElement(By.xpath("//span[text()='Staffs']")).click();
		driver.findElement(By.xpath("//span[text()='Field Operators']")).click();
		driver.findElement(By.xpath("//p[text()='Add New Field Operator']")).click();
		driver.findElement(By.id("first_name")).sendKeys("QA Green");
		driver.findElement(By.id("last_name")).sendKeys("Field");
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+field@gmail.com");
		
		WebElement options = driver.findElement(By.xpath("//select[@name='gender']"));
		Select option= new Select(options);
		option.selectByVisibleText("Male");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.id("password_confirmation")).sendKeys("Dandytech@2022");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Create Field Operator']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert']")));
		
		
	}
	
	public void adminAddZones() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Waste Areas']")));
		
		driver.findElement(By.xpath("//span[text()='Waste Areas']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Scroll down by pixel
		js.executeScript("window.scrollBy(0, 1500);"); // Scrolls 500 pixels down

		// Scroll to a specific element
		WebElement element = driver.findElement(By.xpath("//div[@class='bg-dash py-10 min-h-screen px-3 lg:px-6']")); // Replace with your element's ID or other locator
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	
		driver.findElement(By.xpath("//p[text()='Create Zone']")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("ZONE-Vindex");
		
		Thread.sleep(5000);
		
		WebElement options = driver.findElement(By.xpath("//select[@name='lga']"));
		Select option = new Select(options);
		option.selectByIndex(6);
		
				
		driver.findElement(By.xpath("//input[@id='coordinate']")).sendKeys("091376543212");
		
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		
		
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Save']")));
	}
	
	
	public void fieldOpLaunch() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://admin.mypikaboo.com/");
		
	}
	
	public void fieldOpLogin() {
		driver.findElement(By.xpath("//p[text()='Field Operator']")).click();
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+field3@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Dandytech@2022");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
			
	}
	
	public void fieldAddHouse() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.findElement(By.xpath("//span[text()='Register Home']")).click();
		WebElement options = driver.findElement(By.xpath("//select[@name='title']"));
		Select option = new Select(options);
		option.selectByVisibleText("MR");
		
		WebElement options2 = driver.findElement(By.xpath("//select[@name='gender']"));
		Select option2= new Select(options2);
		option2.selectByVisibleText("Male");
		
		driver.findElement(By.id("first_name")).sendKeys("Daniel");
		driver.findElement(By.id("middle_name")).sendKeys("QA");
		driver.findElement(By.id("last_name")).sendKeys("Greenmouse");
		driver.findElement(By.id("email")).sendKeys("greenmouseapp+home2@gmail.com");
		driver.findElement(By.xpath("(//input[@type='tel'])[1]")).sendKeys("08052937473");
		driver.findElement(By.xpath("(//input[@type='tel'])[2]")).sendKeys("08052937473");
		driver.findElement(By.id("address")).sendKeys("2 metal box road");
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.id("plot_no")).sendKeys("3");
		driver.findElement(By.id("house_number")).sendKeys("2 Obi Rd");
		driver.findElement(By.id("street_name")).sendKeys("Obi Street");
		driver.findElement(By.id("area1")).sendKeys("2");
		driver.findElement(By.id("area2")).sendKeys("5");
		driver.findElement(By.id("quarter")).sendKeys("Boys");
		driver.findElement(By.id("town")).sendKeys("Benin");

		WebElement options3 = driver.findElement(By.xpath("//select[@name='building_type']"));
		Select option3 = new Select(options3);
		option3.selectByVisibleText("2-FLOORS");
		
		WebElement options4 = driver.findElement(By.xpath("//select[@name='zone_id']"));
		Select option4 = new Select(options4);
		option4.selectByVisibleText("Zone QA");
		
		driver.findElement(By.xpath("(//input[@name='facility'])[4]")).click();
		driver.findElement(By.xpath("(//input[@name='flat'])[2]")).click();
		driver.findElement(By.xpath("(//input[@name='shop'])[2]")).click();
		
		WebElement options5 = driver.findElement(By.xpath("//select[@name='purpose_built_facility']"));
		Select option5 = new Select(options5);
		option5.selectByVisibleText("Hotel");
		
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		String filePath = "//Users//user//Downloads//House2.jpeg";
		fileInput.sendKeys(filePath);
	
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
		//Thread.sleep(5000);
		
		//driver.findElement(By.xpath("//span[text()='Home Residents']")).click();
		//driver.switchTo().alert().accept();
		//Thread.sleep(30000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Submit']")));
		driver.findElement(By.xpath("//span[text()='Home Residents']")).click();
	}
	
	


	
}
