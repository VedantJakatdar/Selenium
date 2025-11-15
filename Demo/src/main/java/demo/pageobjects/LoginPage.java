package demo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.absractcomponents.AbstractComponents;

public class LoginPage extends AbstractComponents {
	
	public WebDriver driver;
	
	@FindBy(id = "userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	By toast = By.id("toast-container");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void launch() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getLoginErrorMessage() {
		return waitForElementToBeVisibleText(toast);
	}

	public HomePage goTo(String emailId, String passwordId) {
		email.sendKeys(emailId);
		password.sendKeys(passwordId);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.click();
		return new HomePage(driver);
	}

}
