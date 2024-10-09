package TestNG.DataProviderTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest1 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/login"); // replace with actual login URL
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        // Username and password sets
        return new Object[][] {
            {"tomsmith", "SuperSecretPassword!"},
            {"invaliduser", "wrongpassword"},
            {"user3", "password3"}
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate username and password fields
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement passwordField = driver.findElement(By.id("password"));

        // Enter username and password
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Locate and click the login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Verify login success or failure based on the expected URL after login
        if (username.equals("tomsmith") && password.equals("SuperSecretPassword!")) {
            wait.until(ExpectedConditions.urlContains("/secure"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/secure"), "Expected to be on the secure page after successful login.");
        } else {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"), "Expected error message for invalid login.");
        }
    }

    @AfterMethod
    public void tearDown() {
        // Quit the driver after each test
        if (driver != null) {
            driver.quit();
        }
    }
}
