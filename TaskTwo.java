package tasks;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalTime;


public class TaskTwo {
	public static void main(String[] args) throws InterruptedException {
		LocalTime now = LocalTime.now();
		if (now.isBefore(LocalTime.of(18, 0)) || now.isAfter(LocalTime.of(19, 0))) {
			System.out.println("Allowed only between 6PM and 7PM");
			return;
		}

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		driver.findElement(By.linkText("Signup / Login")).click();
		driver.findElement(By.name("email")).sendKeys("june292025@gmail.com");
		driver.findElement(By.name("password")).sendKeys("june292025");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.xpath("//a[text()=\" Products\"]")).click();
		// Search a product
		driver.findElement(By.name("search")).sendKeys("Jeans");
		driver.findElement(By.id("submit_search")).click();
		Thread.sleep(2000);

		// Click 'Add to cart' for first item
		driver.findElement(By.xpath("(//a[text()='Add to cart'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

		// Go to cart
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		Thread.sleep(1000);

		// Get price
		String priceStr = driver.findElement(By.xpath("//p[@class=\"cart_total_price\"]")).getText()
				.replaceAll("[^0-9]", "");
		int price = Integer.parseInt(priceStr);
		if (price > 500) {
			driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();
			driver.findElement(By.xpath("//a[text()='Place Order']")).click();
			driver.findElement(By.name("name_on_card")).sendKeys("June29");
			driver.findElement(By.name("card_number")).sendKeys("123456789012");
			driver.findElement(By.name("cvc")).sendKeys("311");
			driver.findElement(By.name("expiry_month")).sendKeys("10");
			driver.findElement(By.name("expiry_year")).sendKeys("2026");
			driver.findElement(By.xpath("//button[text()='Pay and Confirm Order']")).click();
			String c = driver.findElement(By.xpath("//b[text()=\"Order Placed!\"]")).getText();
			if (c.equals("ORDER PLACED!")) {
				System.out.println("Your order of price " + price + "/- is placed successfully!");
			}
		} else {
			System.out.println("Price is below ₹500: " + price);
		}

		driver.quit();
	}
}
