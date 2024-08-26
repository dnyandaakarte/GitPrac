package AtumationTestingPractice.PageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractClasses.AbstractComponents;

public class ProductCataloge extends AbstractComponents{

	public WebDriver driver;
	
	public ProductCataloge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(xpath = "//div[@role='alert']")
	WebElement alert;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By nameofProduct = By.cssSelector("b");
	By clickRightProduct = By.cssSelector(".card-body button:last-of-type");
	By alertWait = By.xpath("//div[@role='alert']");
	
	public List<WebElement> getProductList() {
		waitForVisibilityToAppear(productsBy);
		return products;
	}
    
	public String selectRequiredProducts(String[] productname, List<WebElement> productlist) {
		String alerttext = null;
		for(String str:productname) {
			WebElement prod = productlist.stream().filter(p->p.findElement(nameofProduct).getText().equals(str)).findFirst().orElse(null);
			prod.findElement(clickRightProduct).click();
			waitForVisibilityToAppear(alertWait);
			alerttext = alert.getText();
			waitForInvisibility(spinner);			
		}
		return alerttext;		
	}
	
	public CartPage goToCartMethod() {
		goToCart();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage() {
    	goToOrder();
    	OrderPage orderpage = new OrderPage (driver);
    	return orderpage;
	}
}