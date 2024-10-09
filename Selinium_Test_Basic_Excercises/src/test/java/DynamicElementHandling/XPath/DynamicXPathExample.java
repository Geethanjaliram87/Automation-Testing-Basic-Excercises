package DynamicElementHandling.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DynamicXPathExample {
    public static void main(String[] args) {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the website
            driver.get("https://www.ajio.com/");

            // Create an explicit wait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Wait for the specific element identified by the given XPath to be visible
            WebElement ulElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='appContainer']/div[1]/div/header/div[3]/div[1]/ul")));

            // Locate all child elements within the specified <ul> (for example, list items)
            List<WebElement> listItems = ulElement.findElements(By.tagName("li")); // Assuming the items are in <li> tags
            
            // Check if any items are found and print their details
            if (!listItems.isEmpty()) {
                System.out.println("Found the following items in the list:");
                for (WebElement item : listItems) {
                    // Print each item's text or other relevant information
                    System.out.println(item.getText());
                }
            } else {
                System.out.println("No items found in the specified list.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up and quit the driver
            driver.quit();
        }
    }
}


