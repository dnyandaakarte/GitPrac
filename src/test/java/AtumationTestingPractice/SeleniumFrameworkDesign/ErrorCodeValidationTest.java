package AtumationTestingPractice.SeleniumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTestingPractice.TestComponents.BaseTest;
import AutomationTestingPractice.TestComponents.Retry;

public class ErrorCodeValidationTest extends BaseTest{
	
	
	@Test(retryAnalyzer = Retry.class)
	public void errorCodeVerfication() {
		String message = landingPage.loginDetailsValidation("testautomation123@gmail.com", "pass");
		Assert.assertEquals(message, "Incorrect email or password.");
	}
}
