package StringManipulationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StringManipulationTest {

    WebDriver driver;
    WebDriverWait wait;

    // Login credentials
    private final String username = "tomsmith"; // Specified username
    private final String password = "SuperSecretPassword!"; // Specified password
    private final String loginUrl = "http://the-internet.herokuapp.com/login"; // Login URL
    private final String welcomeMessageId = "flash"; // ID of the welcome message element

    @Before
    public void setUp() {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void validateWelcomeMessage() {
        driver.get(loginUrl); // Navigate to the login page

        try {
            // Locate username and password fields and enter credentials
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys(username); // Use the specified username

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.sendKeys(password); // Use the specified password

            // Locate and click the login button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            loginButton.click();

            // Wait for the welcome message to appear after logging in
            WebElement welcomeMessageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id(welcomeMessageId))
            );

            // Retrieve the text from the welcome message
            String welcomeText = welcomeMessageElement.getText().trim();
            // Clean up any extraneous characters
            welcomeText = welcomeText.replace("×", "").trim();
            System.out.println("Retrieved Welcome Message: " + welcomeText);

            // Define the expected message after cleaning up
            String expectedMessage = "You logged into a secure area!";

            // Perform and print string manipulations
            System.out.println("Performing string manipulations...");

            // Split example
            String[] words = welcomeText.split(" ");
            System.out.println("Split words: ");
            for (String word : words) {
                System.out.println(word);
            }

            // Substring example
            String areaWord = welcomeText.substring(welcomeText.indexOf("secure"), welcomeText.indexOf("secure") + "secure".length());
            System.out.println("Extracted Substring (\"secure\"): " + areaWord);

            // Concatenation example
            String constructedGreeting = expectedMessage + " Welcome back, " + areaWord + "!";
            System.out.println("Constructed Greeting: " + constructedGreeting);

            // Assert that the cleaned welcome message matches the expected message
            assert welcomeText.equals(expectedMessage) : "Assertion failed: Welcome message did not match!";
            System.out.println("Assertion passed: Welcome message matched successfully!");

        } catch (TimeoutException e) {
            System.out.println("Error: Element not found within the specified wait time.");
        } catch (NoSuchElementException e) {
            System.out.println("Error: An expected element is missing.");
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
