package CustomException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomExceptionExample {
    public static void main(String[] args) {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the e-commerce site
            driver.get("https://www.ajio.com/");

            // Set a WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Use the provided XPath to locate the element
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"appContainer\"]/div[2]/div/header/div[3]/div[1]/ul")));
                if (!element.isDisplayed()) {
                    throw new ElementNotVisibleException("The specified element is not visible.");
                }
                System.out.println("Element is visible.");
            } catch (ElementNotVisibleException e) {
                System.out.println("Custom Exception caught: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up and quit the driver
            driver.quit();
        }
    }
}
