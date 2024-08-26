package AtumationTestingPractice.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractClasses.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	List<WebElement> f;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="tbody tr")
	List<WebElement> tableRows;
	
	By tableContents = By.cssSelector("tbody tr");
	By orderIdIs = By.cssSelector("th");
	By delProductId = By.cssSelector(".btn-danger");
	By invisibleAlert = By.xpath("//div[@role='alert']");
	
	public boolean confirmOrdersAndDelete(List<String> myList) {
		waitForVisibilityOfDropdownList(tableContents);
		boolean productConfirmed = false;
		f = tableRows;
		int j=0;
		for(int i=myList.size()-1;i>=0;i--) {
		String g = f.get(i).findElement(orderIdIs).getText();
		System.out.println("from orders page table "+g.replace("|", "").trim());
		System.out.println("from mylist confirmation page "+myList.get(j));
		if((g.replace("|", "").trim()).equals(myList.get(j))) {
			productConfirmed = true;
		}
		else {
			productConfirmed = false;
		}
		j++;
		}
		
			for(int i=myList.size()-1;i>=0;i--) {
			String h = myList.get(i);			System.out.println(h);
			WebElement delProd = f.stream().filter(n->n.findElement(orderIdIs).getText().equals(h)).findFirst().orElse(null);
			waitForOrdersPageToLoad(invisibleAlert);
			Actions a = new Actions(driver);
			a.moveToElement(delProd.findElement(delProductId)).click().build().perform();
			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			waitForOrdersPageToLoad(invisibleAlert);
			
		}
			return productConfirmed;
	}
}
