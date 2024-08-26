package AtumationTestingPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractClasses.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	 
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css = ".form__cc div:nth-child(2) div:nth-child(2) input")
	WebElement cvvName;
	
	@FindBy (css = ".form-group input")
	WebElement dropdownText;
	
	@FindBy (css=".list-group button")
	List<WebElement> lstTaken;
	
	@FindBy (css=".action__submit")
	WebElement submitBtn;
	
	By list = By.cssSelector(".list-group button");
	By textIndia = By.cssSelector("span");
	
	public ConfirmationPage checkOutDetails(String country) {
		waitForVisibilityToAppearEle(cvvName);
		cvvName.sendKeys("abc");
		dropdownText.sendKeys("ind");
		waitForVisibilityOfDropdownList(list);
		List<WebElement> lst = lstTaken;
		WebElement requiredCountry = lst.stream().filter(p->p.findElement(textIndia).getText().matches(country)).findFirst().orElse(null);
		requiredCountry.click();
		waitForVisibilityToAppearEle(submitBtn);
		submitBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
		
	}
	
}
