package PolimorphismTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.time.Duration;

public class PolymorphismTest {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @Test
    public void testElementInteraction() {
        driver.get("http://the-internet.herokuapp.com/login");

        // Using polymorphism to handle different elements
        InputField usernameField = new InputField(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))));
        usernameField.sendKeys("tomsmith");

        InputField passwordField = new InputField(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))));
        passwordField.sendKeys("SuperSecretPassword!");

        Button loginButton = new Button(driver.findElement(By.cssSelector("button[type='submit']")));
        loginButton.click();

        // Verifying the login
        wait.until(ExpectedConditions.urlContains("secure"));
        System.out.println("Login test completed successfully with polymorphism to handle different elements on a webpage like Button, Element, Input Field and Link");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
