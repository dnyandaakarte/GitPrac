package AtumationTestingPractice.SeleniumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTestingPractice.TestComponents.BaseTest;

public class RSALoginTest extends BaseTest{
	 
	@Test
	public void loginToApp () {
		String str = landingPage.loginDetailsValidation("shetty@test.com", "Test@123");
		Assert.assertEquals(str, "Login Successfully");
	}
}
