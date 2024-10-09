package TestCaseReuability.Inheritance;
import org.junit.Test;

public class DashboardTest extends BaseTest {

    @Test
    public void testDashboardTitle() {
        login();  // Reuse login from BaseTest
        driver.get("https://example.com/dashboard");  // replace with the actual dashboard URL
        String title = driver.getTitle();
        System.out.println("Dashboard Page Title: " + title);
        // Add assertions to verify the title
    }
    
    // Additional tests specific to the dashboard can be added here
}
