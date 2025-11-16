package demo.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demo.data.Data;
import demo.pageobjects.CartPage;
import demo.pageobjects.HomePage;
import demo.pageobjects.OrderPage;
import demo.pageobjects.PlaceOrder;
import demo.resources.ExcelData;
import demo.testcomponents.BaseTest;

public class CreateOrderTest extends BaseTest {
	
	
	
//	@Test(dataProvider = "getData",groups = {"purchase"}) //String email, String password, String[] productsAdd, String country
//	public void placeOrder(HashMap<String, String> input) throws InterruptedException, IOException {
//		HomePage homePage = loginPage.goTo(input.get("email") ,input.get("password"));
//		CartPage cartPage = homePage.addProductsToCart(input.get("productsAdd"));
//		PlaceOrder placeOrder = cartPage.performCartActions(input.get("productsAdd"));
//		String finalText = placeOrder.placeOrder(input.get("country"));
//		Assert.assertEquals(finalText, "Order Placed Successfully");
//	}
	
	@Test(dataProvider = "getData",groups = {"purchase"}) //String email, String password, String[] productsAdd, String country
	public void placeOrder(List<String> input) throws InterruptedException, IOException {
		HomePage homePage = loginPage.goTo(input.get(1) ,input.get(2));
		CartPage cartPage = homePage.addProductsToCart(input.get(3));
		PlaceOrder placeOrder = cartPage.performCartActions(input.get(3));
		String finalText = placeOrder.placeOrder(input.get(4));
		Assert.assertEquals(finalText, "Order Placed Successfully");
	}
	
	@Test(dependsOnMethods = {"placeOrder"})
	public void verifyAddedProductsOnCartPage() throws InterruptedException, IOException {
		String email = "golu123@gmail.com";
		String password = "Golu@123";
		String[] productsAdd = {"ZARA COAT 3", "iphone 13 pro"};
		HomePage homePage = loginPage.goTo(email, password);
		OrderPage orderPage = homePage.ordersPage();
		orderPage.checkOrderHistory(productsAdd);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		String[] TestNames = {"Sample Test" ,"Stand Alone Test"};
		List<Object[]> allData = new ArrayList<Object[]>();
		//Test
		for(int i=0;i<TestNames.length;i++) {
			List<Object> data = ExcelData.getExcelData(System.getProperty("user.dir")+"//src//main//java//demo//resources//Data.xlsx", TestNames[i], "Sheet1", "TestCaseName");
			allData.add(new Object[] { data });
			}
		return allData.toArray(new Object[0][]);
		
//		List<Object> data = ExcelData.getExcelData("D:\\Siddhu\\Data.xlsx", "Stand Alone Test", "Sheet1", "TestCaseName");
//		List<Object> data1 = ExcelData.getExcelData("D:\\Siddhu\\Data.xlsx", "Sample Test", "Sheet1", "TestCaseName");
//		return new Object[][] {{data},{data1}};
		
//		List<HashMap<String, String>> data = Data.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\demo\\data\\data.json");
//		return new Object[][] {{data.get(0)},{data.get(1)}};
		
//		HashMap<Object, Object> map = new HashMap<Object, Object>();1
//		
//		map.put("email", "golu123@gmail.com");
//		map.put("password", "Golu@123");
//		map.put("productsAdd","ZARA COAT 3");
//		map.put("country", "Ind");
//		
//		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
//		
//		map1.put("email", "siddhu@patil.com");
//		map1.put("password", "Siddhu@123");
//		map1.put("productsAdd","ADIDAS ORIGINAL");
//		map1.put("country", "Ind");
//		
//		return new Object[][] {{map},{map1}};
		
		
//		return new Object[][] {{"golu123@gmail.com","Golu@123",new String[] {"ZARA COAT 3","IPHONE 13 PRO"},"Ind"},
//			{"siddhu@patil.com","Siddhu@123",new String[] {"ADIDAS ORIGINAL"},"Ind"}};
			
			
	}
}
