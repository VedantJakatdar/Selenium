package demo.absractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	public WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orders;
	
	public void waitForElementToBeVisible(By Locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}
	
	public String waitForElementToBeVisibleText(By Locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String text = wait.until(ExpectedConditions.visibilityOf(driver.findElement(Locator))).getText();
		return text;
	}
	
	public void waitForElementToBeInvisible(By Locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
	}
	
	public void scrollingToWebElement(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}
	
	public void goToOrdersPage() {
		orders.click();
	}

}
