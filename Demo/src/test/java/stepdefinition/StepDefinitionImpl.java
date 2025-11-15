package stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import demo.pageobjects.CartPage;
import demo.pageobjects.HomePage;
import demo.pageobjects.LoginPage;
import demo.pageobjects.OrderPage;
import demo.pageobjects.PlaceOrder;
import demo.testcomponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LoginPage loginPage;
	public HomePage homePage;
	public CartPage cartPage;
	public PlaceOrder placeOrder;
	public String text;
	public OrderPage orderPage;
	
	@Given("I landed on login page")
	public void I_landed_on_login_page() throws IOException {
		loginPage = launchApplication();
	}
	
	@Given("^I logged in with (.+) and (.+)$")
	public void I_logged_in_with_and(String username, String password) throws IOException {
	    homePage = loginPage.goTo(username, password);
	}
	
	@When("^I added (.+) to the cart$") 
	public void I_added_to_the_cart(String product) throws InterruptedException {
		cartPage = homePage.addProductsToCart(product);
	}
	
	@When("I went to the orders page") 
	public void I_went_to_the_orders_page() {
		orderPage = homePage.ordersPage();
	}
	
	@And("^I verified (.+) on cart page$")
	public void I_verified_on_cart_page(String product) throws InterruptedException {
		placeOrder = cartPage.performCartActions(product);
	}
	
	@And("I selected country {string}")
	public void I_selected_country(String country) throws InterruptedException {
		text = placeOrder.placeOrder(country);
	}
	
	@Then("I got message {string}") 
	public void I_got_message(String msg) {
		Assert.assertEquals(text, msg);
		tearDown();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String expText) {
		String actText = loginPage.getLoginErrorMessage();
		Assert.assertEquals(actText, expText);
		tearDown();
	}
	
    @Then("^I verified (.+) on orders page$")
    public void I_verified_on_orders_page(String product) {
    	String[] products = {product};
    	orderPage.checkOrderHistory(products);
    	tearDown();
    }

}
