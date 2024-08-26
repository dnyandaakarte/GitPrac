package AtumationTestingPractice.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractClasses.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css=".hero-primary")
	WebElement confirmedText;
	
	@FindBy (xpath = "//tbody/tr/td/label[@class='ng-star-inserted']")
	List<WebElement> ids;
	
	@FindBy (css = "[routerlink*='myorders']")
	WebElement ordersBtn;
	
	By textToConfirm = By.cssSelector(".hero-primary");
	By orderIds = By.xpath("//tbody/tr/td/label[@class='ng-star-inserted']");
	
	public String returnStringToConfirm () {
		waitForVisibilityToAppear(textToConfirm);
		String str = (confirmedText).getText();
		return str;
	}	
	public List<String> confirmationOfOrder () {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
		waitForVisibilityOfDropdownList(orderIds);
		List<WebElement> lst1 = ids;
		List<String> myList = new ArrayList<>();
		for(int i=0;i<lst1.size();i++) {
			myList.add(lst1.get(i).getText().replace("|", "").trim());
		}
		return myList;
	}
}
