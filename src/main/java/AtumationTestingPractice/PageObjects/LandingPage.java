package AtumationTestingPractice.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractClasses.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	public WebDriver driver;
	
	public LandingPage (WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCataloge loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCataloge productCataloge = new ProductCataloge(driver);
		return productCataloge;
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public String loginDetailsValidation(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		waitForVisibilityToAppearEle(errorMessage);
		String str = errorMessage.getText();
		return str;
	}
}
