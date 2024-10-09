package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("HomePage object created. Memory allocated.");
    }

    // Placeholder method for homepage actions
    public void loadHomePage() {
        driver.get("https://www.ajio.com/");
    }
}
