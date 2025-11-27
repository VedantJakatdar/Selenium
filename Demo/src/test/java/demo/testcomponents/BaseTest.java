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
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
				"/src/main/java/demo/resources/global.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null 
				? System.getProperty("browser") 
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			// Detect OS for chromedriver path
			String osName = System.getProperty("os.name").toLowerCase();
			String driverPath;

			if (osName.contains("windows")) {
				driverPath = System.getProperty("user.dir") + "/WebDriver/chromedriver.exe";
			} else {
				driverPath = System.getProperty("user.dir") + "/WebDriver/chromedriver";
			}

			System.setProperty("webdriver.chrome.driver", driverPath);

			ChromeOptions options = new ChromeOptions();

			// Disable password manager leak detection
			options.setExperimentalOption("prefs", new HashMap<String, Object>() {{
				put("profile.password_manager_leak_detection", false);
			}});

			// ADD THIS ALWAYS — Fixes Chrome 109+ cross-origin restrictions
			options.addArguments("--remote-allow-origins=*");

			// Headless mode for Linux / CI / Azure VM
			if (browserName.contains("headless")) {
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--disable-gpu");
				options.addArguments("--disable-extensions");
			}

			// Create driver (safe)
			driver = new ChromeDriver(options);

			// Maximize only if NOT headless
			if (!browserName.contains("headless")) {
				driver.manage().window().maximize();
			}
		}

		// You can add Firefox / Edge here later

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
		// Prevent "driver is null" crash
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception ignored) {}
		}
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		FileUtils.copyFile(file, new File(path));
		return path;
	}
}
