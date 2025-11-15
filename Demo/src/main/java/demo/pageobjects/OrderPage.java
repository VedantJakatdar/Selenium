package demo.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import demo.absractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	public WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//tbody/tr/td[2]")
	List<WebElement> orderHistoryNames;
	
	public void checkOrderHistory(String[] productsAdd) {
		int count = 0;
		List<String> productsList = Arrays.asList(productsAdd);
		for(int i=0;i<orderHistoryNames.size();i++) {
			String orderName = orderHistoryNames.get(i).getText();
			if(productsList.contains(orderName)) {
				Assert.assertTrue(true);
				count++;
				if(count==productsAdd.length) {
					break;
				}
			}else {
				Assert.assertTrue(false);
			}
		}
	}
}
