package ExceptionHandling.ElementNotFound;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.*;
import java.time.Duration;

public class ElementNotFoundTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testElementPresence() {
        driver.get("https://example.com");

        try {
            // Attempt to find a specific element by its ID
            WebElement element = driver.findElement(By.id("nonExistentElement"));
            // If element is found, perform any action, like clicking or printing text
            System.out.println("Element found with text: " + element.getText());

        } catch (NoSuchElementException e) {
            // Handle case where the element was not found
            System.out.println("The specified element was not found on the page. ");
            System.out.println(" Please check if the element ID is correct or if the element exists.");

        } catch (Exception e) {
            // General exception handling for any other unexpected issues
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

