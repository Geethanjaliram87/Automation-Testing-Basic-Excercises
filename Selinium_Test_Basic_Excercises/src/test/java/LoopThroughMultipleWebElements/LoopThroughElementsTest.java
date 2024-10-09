package LoopThroughMultipleWebElements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import java.time.Duration;
import java.util.List;

public class LoopThroughElementsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Extended wait time
    }

    @Test
    public void testTableRowIteration() {
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        try {
            // Wait for the table to load
            System.out.println("Waiting for table rows to load...");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customers")));

            // Get all rows in the table body
            List<WebElement> rows = driver.findElements(By.cssSelector("#customers tbody tr"));
            System.out.println("Number of rows found: " + rows.size());

            // Loop through each row and print the data
            for (WebElement row : rows) {
                // Print each cell's text in the row
                List<WebElement> cells = row.findElements(By.tagName("td"));
                for (WebElement cell : cells) {
                    System.out.print(cell.getText() + " ");
                }
                System.out.println(); // Newline for each row
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}