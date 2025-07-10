package tasks;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Random;
public class TaskFour {

    public static void main(String[] args) throws InterruptedException {

        // Run only between 6 PM - 7 PM
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.of(18, 0)) || now.isAfter(LocalTime.of(19, 0))) {
            System.out.println("Test allowed only between 6 PM and 7 PM.");
            return;
        }

        //Validate username
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
		driver.findElement(By.linkText("Signup / Login")).click();
		driver.findElement(By.name("email")).sendKeys("june292025@gmail.com");
		driver.findElement(By.name("password")).sendKeys("june292025");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		String username = driver.findElement(By.xpath("//a[text()=\" Logged in as \"]/b")).getText();
        if (username.length() != 10 || !Pattern.matches("^[a-zA-Z0-9]*$", username)) {
            System.out.println("Username invalid. Must be 10 characters with no special characters.");
            return;
        } else {
            System.out.println("Valid username: " +username);
        }

        

        // Navigate to Products page
        driver.findElement(By.xpath("//a[text()=\" Products\"]")).click();
        Thread.sleep(3000);

        //  Add 4 products to cart using hover and click
        Actions actions = new Actions(driver);
        int totalINR = 0;
        Random rand = new Random();

        int randomNum = rand.nextInt(5 - 1 + 1) + 1;
        for (int i = 1; i <= randomNum; i++) {
            // Hover over product card
            WebElement productCard = driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])[" + i + "]"));
            actions.moveToElement(productCard).perform();
            Thread.sleep(1000);

            // Click Add to Cart button
            WebElement addButton = driver.findElement(By.xpath("(//div[@class='product-overlay'])[" + i + "]//a[contains(text(),'Add to cart')]"));
            addButton.click();
            Thread.sleep(2000);

            // Click Continue Shopping
            WebElement continueBtn = driver.findElement(By.cssSelector(".btn.btn-success.close-modal"));
            continueBtn.click();
            Thread.sleep(1500);
        }

        // Go to Cart
        driver.findElement(By.partialLinkText("Cart")).click();
        Thread.sleep(3000);

        // Extract total prices
        List<WebElement> prices = driver.findElements(By.xpath("//td[@class='cart_price']/p"));
        
        for (WebElement priceElement : prices) {
            String priceText = priceElement.getText().replaceAll("[^0-9]", "");
            if (!priceText.isEmpty()) {
                totalINR += Integer.parseInt(priceText);
            }
        }


        if (totalINR < 2000) {
            System.out.println(totalINR+" is less than ₹2000");
        }
        else {
        	System.out.println("Total price: "+totalINR+"!!");
        }

        //Close browser
        driver.quit();
    }
}
