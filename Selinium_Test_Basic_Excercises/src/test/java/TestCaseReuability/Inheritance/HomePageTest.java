package TestCaseReuability.Inheritance;
import org.junit.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageTitle() {
        login();  // Reuse the login method from BaseTest
        String title = driver.getTitle();
        System.out.println("Home Page Title: " + title);
        // Add assertions to verify the title
    }
    
    // Additional tests specific to the homepage can be added here
}
