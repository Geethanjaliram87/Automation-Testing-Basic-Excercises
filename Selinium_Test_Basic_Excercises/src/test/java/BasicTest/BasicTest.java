package BasicTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Specify the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        
        // Initialize Chrome WebDriver
        driver = new ChromeDriver();
    }

    @Test
    public void openBrowserAndPrintTitle() {
        // Open a website
        driver.get("https://example.com");
        
        // Print the page title
        System.out.println("Page Title: " + driver.getTitle());
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
