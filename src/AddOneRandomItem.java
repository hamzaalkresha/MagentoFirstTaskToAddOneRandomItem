import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";

	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();

	}

	@Test (invocationCount = 5)
	public void addOneRandomItemToThecart() throws InterruptedException {
		Random rand = new Random();
		int RandomIndex = rand.nextInt(6);
		driver.get(URL);

		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> ListOfItems = Container.findElements(By.tagName("li"));
		
			ListOfItems.get(RandomIndex).click();
			Thread.sleep(2000);
			if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push-it")) {
				WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
				addToCartButton.click();
				
			}
			else {
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
				
			}

		}

	}

