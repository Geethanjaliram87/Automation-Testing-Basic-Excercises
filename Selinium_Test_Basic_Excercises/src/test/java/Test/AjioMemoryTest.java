package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;
import pageobjects.HomePage;

public class AjioMemoryTest {
    public static void main(String[] args) {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\chromedriver-win64\\chromedriver-win64"
        		+ "\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        // Check memory before instantiating page objects
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory usage before creating page objects: " + memoryBefore + " bytes");

        // Create instances of LoginPage and HomePage
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage(); // Open the login page

        HomePage homePage = new HomePage(driver);
        homePage.loadHomePage(); // Load the home page

        // Check memory after instantiating page objects
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory usage after creating page objects: " + memoryAfter + " bytes");
        System.out.println("Memory difference: " + (memoryAfter - memoryBefore) + " bytes");

        // Clean up and quit the driver
        driver.quit();
    }
}
