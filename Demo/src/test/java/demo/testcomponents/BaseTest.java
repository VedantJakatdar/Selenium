package demo.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import demo.pageobjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//demo//resources//global.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "D:\\Driver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//WebDriver//chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
			    options.addArguments("--disable-gpu");
			    options.addArguments("--no-sandbox");
			    options.addArguments("--disable-dev-shm-usage");
			}
			options.setExperimentalOption("prefs", new HashMap<String, Object>(){{
			put("profile.password_manager_leak_detection", false);}});
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("edge")) {
			//edge code
		} else if (browserName.equalsIgnoreCase("firefox")) {
			//firefox code
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.launch();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png"));
		return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	}
}
