package ExplicitWaitTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ExplicitWaitTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1"); // Example URL with dynamically loaded elements
    }

    @Test
    public void dynamicElementLoadTest() {
        // Initialize WebDriverWait for a 10-second timeout
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click the Start button to begin dynamic loading
        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#start button")));
        startButton.click();

        // Wait for the loading to complete and Hello World message to appear
        WebElement helloWorldText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        Assert.assertEquals(helloWorldText.getText(), "Hello World!", "The expected text is not displayed.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

