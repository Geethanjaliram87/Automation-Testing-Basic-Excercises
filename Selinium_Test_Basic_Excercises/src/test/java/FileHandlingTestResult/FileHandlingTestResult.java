package FileHandlingTestResult;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

public class FileHandlingTestResult {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testMultipleWindows() {
        String testName = "testMultipleWindows";
        boolean testPassed = false;

        try {
            driver.get("https://www.example.com");
            String mainWindowHandle = driver.getWindowHandle();

            // Open additional windows
            ((JavascriptExecutor) driver).executeScript("window.open('https://www.selenium.dev');");
            ((JavascriptExecutor) driver).executeScript("window.open('https://www.w3schools.com');");

            Set<String> windowHandles = driver.getWindowHandles();
            System.out.println("Total windows/tabs opened: " + windowHandles.size());

            Iterator<String> iterator = windowHandles.iterator();

            while (iterator.hasNext()) {
                String handle = iterator.next();
                driver.switchTo().window(handle);
                
                // Explicit wait for page title to load
                wait.until(d -> !driver.getTitle().isEmpty());
                System.out.println("Switched to window with title: " + driver.getTitle());
            }

            driver.switchTo().window(mainWindowHandle);
            System.out.println("Switching back to main window with title: " + driver.getTitle());

            // Closing each window
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                System.out.println("Closing window with title: " + driver.getTitle());
                try {
                    driver.close();
                } catch (Exception e) {
                    System.out.println("Error closing window: " + e.getMessage());
                }
            }

            testPassed = true; // Mark test as passed if all actions are successful

        } catch (Exception e) {
            System.out.println("Test failed due to: " + e.getMessage());
        } finally {
            writeTestResultToFile(testName, testPassed);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void writeTestResultToFile(String testName, boolean testPassed) {
        String filePath = "test_results.txt"; // Specify your file path here
        String result = testPassed ? "PASS" : "FAIL";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Test Name: " + testName + ", Result: " + result + ", Timestamp: " + timestamp);
            writer.newLine();
            System.out.println("Test result written to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
