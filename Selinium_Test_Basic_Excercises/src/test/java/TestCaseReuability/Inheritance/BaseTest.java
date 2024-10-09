package TestCaseReuability.Inheritance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com");  // replace with your actual test URL
    }

    public void login() {
        // Perform login actions (e.g., find username and password fields, click login)
        System.out.println("Performing login actions...");
        // Implement actual login steps here, using driver.findElement(...)
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
