package AutomationTestingPractice.StepDefination;

import java.io.IOException;

import AutomationTestingPractice.TestComponents.BaseTest;
import io.cucumber.java.en.Given;

public class StepDefinationImp extends BaseTest{
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		lunchApplication();
	}
	
}
