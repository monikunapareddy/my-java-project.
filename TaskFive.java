package tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalTime;

public class TaskFive {

    public static void main(String[] args) throws InterruptedException {
        // Set the allowed test time: 12 PM to 3 PM
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(12, 0);
        LocalTime end = LocalTime.of(15, 0);

        if (now.isBefore(start) || now.isAfter(end)) {
            System.out.println("Test can only run between 12 PM and 3 PM.");
            return;
        }
        WebDriver driver = new ChromeDriver();
            // Open site
            driver.get("https://automationexercise.com/");
            driver.manage().window().maximize();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Signup / Login")).click();
			driver.findElement(By.name("email")).sendKeys("june292025@gmail.com");
    		driver.findElement(By.name("password")).sendKeys("june292025");
    		driver.findElement(By.xpath("//button[text()='Login']")).click();
            Thread.sleep(3000);

            //Get the profile name after login
            String username = driver.findElement(By.xpath("//a[text()=\" Logged in as \"]/b")).getText();
            //Validate the username
            System.out.println("Username: " + username);

            //Check if name contains invalid characters
            if (username.matches(".*[ACGILK].*")) {
                System.out.println("Invalid  Username! It contains forbidden characters: A, C, G, I, L, K");
            } else {
                System.out.println("Username is valid.");
            }
            driver.quit();
        }
    }
