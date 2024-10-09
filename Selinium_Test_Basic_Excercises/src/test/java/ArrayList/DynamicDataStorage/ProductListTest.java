package ArrayList.DynamicDataStorage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductListTest {
    private WebDriver driver;
    private ArrayList<String> productNames; // ArrayList to store product names dynamically

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        productNames = new ArrayList<>(); // Initialize the ArrayList
    }

    @Test
    public void testProductNames() {
        driver.get("https://www.amazon.in");
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-search-bar-form")));

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("laptop");
        driver.findElement(By.id("nav-search-submit-button")).click();
        
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".s-main-slot .s-result-item .a-text-normal")));

        List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item .a-text-normal"));

        // Loop through the web elements and add product names to the ArrayList
        for (WebElement product : products) {
            String productName = product.getText();
            if (!productName.isEmpty() && !productName.contains("Check each product page for other buying options")) {
                productNames.add(productName); // Dynamically add product names
            }
        }

        // Print and validate each product name in the ArrayList
        for (String name : productNames) {
            System.out.println("Product Name: " + name);
            assert !name.isEmpty() : "Product name should not be empty"; // Validation
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
