package demo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.absractcomponents.AbstractComponents;

public class PlaceOrder extends AbstractComponents {
	
	public WebDriver driver;
	
	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement proceed;
	
	@FindBy(css = "input[placeholder='Select Country")
	WebElement country;
	
	@FindBy(xpath = "//section[@class='ta-results list-group ng-star-inserted']/button")
	List<WebElement> buttons;
	
	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;
	
	By toast = By.id("toast-container");
	
	public String placeOrder(String name) throws InterruptedException {
		proceed.click();
		country.sendKeys(name);
		buttons.stream().filter(s -> s.getText().equalsIgnoreCase("india")).findFirst().orElse(null).click();
		scrollingToWebElement("window.scrollBy(0,500);");
		Thread.sleep(500);
		placeOrder.click();
		return waitForElementToBeVisibleText(toast);

	}

}
