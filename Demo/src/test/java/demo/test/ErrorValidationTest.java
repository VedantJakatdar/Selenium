package demo.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import demo.pageobjects.HomePage;
import demo.testcomponents.BaseTest;
import demo.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorValidation"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() {
		HomePage homePage = loginPage.goTo("Vedant@1234", "Vedant@1234");
		String text = loginPage.getLoginErrorMessage();
		Assert.assertEquals(text, "Incorrect email or password.");
	}
}
