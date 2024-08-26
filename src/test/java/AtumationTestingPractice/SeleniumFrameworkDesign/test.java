package AtumationTestingPractice.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("testautomation123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		String[] productname = {"ZARA COAT 3", "IPHONE 13 PRO", "ADIDAS ORIGINAL"};
		for(String str:productname) {
			WebElement prod = products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(str)).findFirst().orElse(null);
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@role='alert']")));
			String alerttext = driver.findElement(By.xpath("//div[@role='alert']")).getText();
			Assert.assertEquals("Product Added To Cart", alerttext);
			try{
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			}
			catch (Exception e){
				continue;
			}
		}
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart"))));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//button[text()='Checkout']"))).click().perform();
		WebElement web = driver.findElement(By.xpath("//div[text()='CVV Code ']"));
		WebElement cvv = driver.findElement(RelativeLocator.with(By.xpath("//input[@type = 'text']")).below(web));
		cvv.sendKeys("abc");
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".list-group button")));
		List<WebElement> lst = driver.findElements(By.cssSelector(".list-group button"));
		String s = "India";
		for(int i=0;i<lst.size();i++) {
			String s1 = lst.get(i).findElement(By.cssSelector("span")).getText();
			if(s.equals(s1)) {
				lst.get(i).click();
				break;
			}
		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String str = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), str);
		js.executeScript("window.scrollTo(0, 0)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr/td/label[@class='ng-star-inserted']")));
		List<WebElement> lst1 = driver.findElements(By.xpath("//tbody/tr/td/label[@class='ng-star-inserted']"));
		List<String> myList = new ArrayList<>();
		for(int i=0;i<lst1.size();i++) {
			myList.add(lst1.get(i).getText().replace("|", "").trim());
		}
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@routerlink='/dashboard/myorders']")));
		try {
			driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();
		} catch (Exception e) {
			js.executeScript("window.scrollTo(0, 0)");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='myorders']")));
			driver.findElement(By.cssSelector("[routerlink*='myorders']")).click();
		}
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("tbody tr")));
		List<WebElement> f = driver.findElements(By.cssSelector("tbody tr"));
		int j=0;
		for(int i=myList.size()-1;i>=0;i--) {
		String g = f.get(i).findElement(By.cssSelector("th")).getText();
		Assert.assertEquals(g.replace("|", "").trim(), myList.get(j));
		j++;
		}
		for(int i=0;i<myList.size();i++) {
			String h = myList.get(i);
			WebElement delProd = f.stream().filter(n->n.findElement(By.cssSelector("th")).getText().equals(h)).findFirst().orElse(null);
			wait.until(ExpectedConditions.refreshed((ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@role='alert']"))))));
			delProd.findElement(By.cssSelector(".btn-danger")).click();
			wait.until(ExpectedConditions.refreshed((ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alert']")))));
		}
		
	}
}
