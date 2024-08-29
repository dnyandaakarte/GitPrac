package AtumationTestingPractice.SeleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import AtumationTestingPractice.PageObjects.CartPage;
import AtumationTestingPractice.PageObjects.CheckoutPage;
import AtumationTestingPractice.PageObjects.ConfirmationPage;
import AtumationTestingPractice.PageObjects.OrderPage;
import AtumationTestingPractice.PageObjects.ProductCataloge;
import AutomationTestingPractice.TestComponents.BaseTest;

public class FrameworkCodeTest extends BaseTest{

	public List<String> myList;
	 
	@Test(dataProvider="getData", groups = {"Purchase"})
	public void addProductToCart(HashMap<String, String> input) throws IOException {	
		ProductCataloge productCataloge = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCataloge.getProductList();
		String[] productname = {"ZARA COAT 3", "IPHONE 13 PRO", "ADIDAS ORIGINAL"};
		String str = productCataloge.selectRequiredProducts(productname, products);
		Assert.assertEquals(str, "Product Added To Cart");
		CartPage cartpage = productCataloge.goToCartMethod();
		CheckoutPage checkoutPage = cartpage.checkout();
		ConfirmationPage confirmationPage =checkoutPage.checkOutDetails("India"); 
		Assert.assertEquals(confirmationPage.returnStringToConfirm(), "THANKYOU FOR THE ORDER.");
        myList = confirmationPage.confirmationOfOrder();
        OrderPage orderPage = productCataloge.goToOrderPage();
		boolean flag = orderPage.confirmOrdersAndDelete(myList);
		Assert.assertTrue(flag);
		System.out.print("Hey");
		System.out.println("changes are moved to git");
	}
	
	
	@DataProvider
	public Object[][] getData() throws JsonProcessingException, IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "testautomation123@gmail.com");
//		map.put("Password", "Test@123");
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@test.com");
//		map1.put("Password", "Test@123");
		List<HashMap<String, String>> data = getJsonDataToMap("C:\\Users\\dakarte\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\AutomationTestingPractice\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1) } };
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"testautomation123@gmail.com", "Test@123"},{"shetty@test.com", "Test@123"}};
//	}
	
}

