package demo.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import demo.absractcomponents.AbstractComponents;

public class HomePage extends AbstractComponents {

	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']")
	List<WebElement> products;
	
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']/div/div/h5")
	List<WebElement> productsName;
	
	@FindBy(xpath = "//div[@class='col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1 mb-3 ng-star-inserted']/div/div/button[2]")
	List<WebElement> productsButton;
	
	By toast = By.id("toast-container");
	
	//By load = By.xpath("//div[@class='ngx-spinner-overlay ng-tns-c31-0 ng-trigger ng-trigger-fadeIn ng-star-inserted']");
	
	public CartPage addProductsToCart(String[] productsAdd) throws InterruptedException {
		Thread.sleep(500);
		scrollingToWebElement("window.scrollBy(0,500);");
		Thread.sleep(500);
		int count = 0;
		for(int i=0;i<products.size();i++) {
			String name = productsName.get(i).getText();
			List<String> input = Arrays.asList(productsAdd);
		    if(input.contains(name)) {
		    	productsButton.get(i).click();
		    	waitForElementToBeVisible(toast);
		    	waitForElementToBeInvisible(toast);
		    	count++;
		    	if(count==productsAdd.length) {
		    		break;
		    	}
		    }
		}
		Thread.sleep(500);
		return new CartPage(driver);
	}
	
	public CartPage addProductsToCart(String productsAdd) throws InterruptedException {
		Thread.sleep(500);
		scrollingToWebElement("window.scrollBy(0,500);");
		Thread.sleep(500);
		for(int i=0;i<products.size();i++) {
			String name = productsName.get(i).getText();
			List<String> input = Arrays.asList(productsAdd);
		    if(input.contains(name)) {
		    	productsButton.get(i).click();
		    	waitForElementToBeVisible(toast);
		    	waitForElementToBeInvisible(toast);
		    	break;
		    }
		}
		Thread.sleep(500);
		return new CartPage(driver);
	}
	
	public OrderPage ordersPage() {
		goToOrdersPage();
		return new OrderPage(driver);
	}
}
