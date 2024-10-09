package TestData.Encapsulation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    TestData testData;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        
        // Using test credentials for a demo login
     // In your setUp() method
        testData = new TestData("tomsmith", "SuperSecretPassword!");

    }

    @Test
    public void testLogin() {
        driver.get("http://the-internet.herokuapp.com/login");

        // Enter username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.sendKeys(testData.getUsername());

        // Enter password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys(testData.getPassword());

        // Click login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Verify login success by checking the URL or a success message
        wait.until(ExpectedConditions.urlContains("secure"));
        System.out.println("Login test completed successfully.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
