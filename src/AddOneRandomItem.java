import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}

	@Test(priority = 1, description = "this is my first test")
	public void addOneRandomItemToThecart() throws InterruptedException {
		Random rand = new Random();
		int RandomIndex = rand.nextInt(4);
		driver.get(URL);

		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> ListOfItems = Container.findElements(By.tagName("li"));

		ListOfItems.get(RandomIndex).click();
		Thread.sleep(2000);
		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push-it")) {
			WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
			addToCartButton.click();

		} else {

			WebElement SizeContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
					
			WebElement colorContainer = driver.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
					
			List<WebElement> TheListOfSizes = SizeContainer.findElements(By.tagName("div"));
			
			List<WebElement> TheListOfColors = colorContainer.findElements(By.tagName("div"));
			
			int randomSize = rand.nextInt(TheListOfSizes.size());
			
			int randomColor = rand.nextInt(TheListOfColors.size());
			
			TheListOfSizes.get(randomSize).click();
			
			TheListOfColors.get(randomColor).click();
			
			WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
			
			addToCartButton.click();
			
			Thread.sleep(3000);

		}

	}

	@Test(priority = 2, description = "this is my second test")
	public void checkoutProcess() throws InterruptedException {
		String CheckoutURL = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(CheckoutURL);
		Thread.sleep(3000);
		WebElement CheckoutButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
		CheckoutButton.click();

	}

	@Test(priority = 3, description = "this is my third test")
	public void signupProcess() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Email = driver.findElement(By.id("customer-email"));
		
		WebElement FirstName = driver.findElement(By.name("firstname"));
		
		WebElement LastName = driver.findElement(By.name("lastname"));
		
		WebElement StreetAddress = driver.findElement(By.name("street[0]"));
		
		WebElement City = driver.findElement(By.name("city"));
		
		WebElement State = driver.findElement(By.name("region_id"));
		
		WebElement PostalCode = driver.findElement(By.name("postcode"));
		
		WebElement Country = driver.findElement(By.name("country_id"));
		
		WebElement PhoneNumber = driver.findElement(By.name("telephone"));
		
		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		
		Email.sendKeys("hamzaqwere@gmail.com");
		
		FirstName.sendKeys("Hamza");
		
		LastName.sendKeys("Alkresha");
		
		StreetAddress.sendKeys("Awjan");
		
		City.sendKeys("Zarqa");
		
		State.sendKeys("Zarqa");
		
		PostalCode.sendKeys("18023");
		
		Country.sendKeys("Jordan");
		
		PhoneNumber.sendKeys("962781962939");
		
		nextButton.click();
		
		Select select = new Select(Country);

//		select.selectByValue("JO");
//		select.selectByIndex(5);
		select.selectByVisibleText("Chile");

	}

}
