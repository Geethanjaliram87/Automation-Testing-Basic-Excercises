package HashMapDataDrivenTesting;
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

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RunWith(Parameterized.class)
public class HashMapDataDrivenTesting {
    private WebDriver driver;
    private String url;
    private String username;
    private String password;
    private String expectedResult;

    // HashMap to store test data
    private static final Map<String, String[]> testData = new HashMap<>();

    static {
        // Initialize test data: {url, username, password, expectedResult}
        testData.put("user1", new String[]{"http://the-internet.herokuapp.com/login", "tomsmith", "SuperSecretPassword!", "You logged into a secure area!"});
        testData.put("user2", new String[]{"http://the-internet.herokuapp.com/login", "invaliduser", "wrongpassword", "Your username is invalid!"});
        // Add more users as needed
    }

    public HashMapDataDrivenTesting(String username, String password, String expectedResult, String url) {
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
        this.url = url;  // URL to be used in the test
    }

    @Before
    public void setUp() {
        // Set the path to the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        
        // Create a new instance of the Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        System.out.println("URL: " + url);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Expected Result: " + expectedResult);
        
        driver.get(url); // Use the URL from the HashMap
        driver.findElement(By.id("username")).sendKeys(username); // Username field ID
        driver.findElement(By.id("password")).sendKeys(password); // Password field ID
        driver.findElement(By.cssSelector("button[type='submit']")).click(); // Login button using CSS selector

        // Wait for the result message to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash"))); // Wait for the result message to be visible

        // Validate the result
        String actualResult = driver.findElement(By.id("flash")).getText(); // Result message ID
        System.out.println("Actual result: " + actualResult); // Print the actual result message
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
    public static Object[][] data() {
        // Create an array of parameters from the HashMap
        return testData.entrySet().stream()
                .map(entry -> {
                    String[] userData = entry.getValue();
                    return new Object[]{userData[1], userData[2], userData[3], userData[0]}; // username, password, expectedResult, url
                })
                .toArray(Object[][]::new);
    }
}
