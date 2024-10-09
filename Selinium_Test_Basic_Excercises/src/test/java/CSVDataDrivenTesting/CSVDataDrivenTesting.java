package CSVDataDrivenTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CSVDataDrivenTesting {
    private WebDriver driver;
    private String username;
    private String password;
    private String expectedResult;

    public CSVDataDrivenTesting(String username, String password, String expectedResult) {
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        driver.get("http://the-internet.herokuapp.com/login"); // Login URL
        driver.findElement(By.id("username")).sendKeys(username); // Username field ID
        driver.findElement(By.id("password")).sendKeys(password); // Password field ID
        driver.findElement(By.cssSelector("button[type='submit']")).click(); // Login button using CSS selector

        // Use the new constructor for WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash"))); // Wait for the result message to be visible

        // Validate the result
        String actualResult = driver.findElement(By.id("flash")).getText(); // Result message ID
        assert actualResult.contains(expectedResult) : "Expected: " + expectedResult + ", but got: " + actualResult;
        System.out.println("Test completed for username: " + username + "\n"); // Indicate test completion
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        ArrayList<Object[]> testData = new ArrayList<>();
        String csvFile = "test_data.csv"; // Path to your CSV file
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(",");
                testData.add(new Object[]{dataRow[0], dataRow[1], dataRow[2]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testData;
    }
}
