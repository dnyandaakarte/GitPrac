package AutomationTestingPractice.AbstractClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy (xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy (css = "[routerlink*='myorders']")
	WebElement ordersBtn;
	
	By orderBtnVisibility = By.cssSelector("[routerlink*='myorders']");
	
	public void waitForVisibilityToAppear(By productsBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(productsBy));
	}
	public void waitForInvisibility(WebElement spinner) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}
	public void waitForVisibilityOfCartProducts(By cartProducts) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(cartProducts)));
	}
	public void waitForVisibilityOfDropdownList(By list) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(list));
	}
	public void waitForVisibilityToAppearEle(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForOrdersPageToLoad(By invisibleAlert) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOfElementLocated(invisibleAlert)));
	}
	
	public void goToCart() {
		waitForInvisibility(spinner);
		cartBtn.click();
	}
	
	public void goToOrder() {
		try {
			ordersBtn.click();
		} catch (Exception e) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, 0)");
			waitForVisibilityToAppear(orderBtnVisibility);
			ordersBtn.click();
		}
	}
}
