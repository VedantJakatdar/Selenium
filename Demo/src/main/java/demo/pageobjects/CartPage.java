package demo.pageobjects;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import demo.absractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	public WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartIcon;
	
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	public PlaceOrder performCartActions(String[] productsAdd) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartIcon);
		Thread.sleep(1000);
		cartIcon.click();
		for(int i=0; i<cartProducts.size(); i++) {
			String cartName = cartProducts.get(i).getText();
			List<String> input = Arrays.asList(productsAdd);
			if(input.contains(cartName)) {
				assertTrue(true);
			}else {
				assertTrue(false);
			}
		}
		scrollingToWebElement("window.scrollBy(0,1000);");
		Thread.sleep(500);
		return new PlaceOrder(driver);
	}
	
	public PlaceOrder performCartActions(String productsAdd) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartIcon);
		Thread.sleep(1000);
		cartIcon.click();
		for(int i=0; i<cartProducts.size(); i++) {
			String cartName = cartProducts.get(i).getText();
			List<String> input = Arrays.asList(productsAdd);
			if(input.contains(cartName)) {
				assertTrue(true);
			}else {
				assertTrue(false);
			}
		}
		scrollingToWebElement("window.scrollBy(0,1000);");
		Thread.sleep(500);
		return new PlaceOrder(driver);
	}
	
}
