package ParallelTestExecution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ParallelTest implements Runnable {
    private String url;

    public ParallelTest(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dr. T.P. YOKESH\\Downloads\\\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Create ChromeOptions for the WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Maximize the browser window

        // Create a new instance of ChromeDriver
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the specified URL
            driver.get(url);
            // Print the title of the page
            System.out.println("Thread: " + Thread.currentThread().getName() + " - Page Title: " + driver.getTitle());
            // Add more interactions if needed
        } finally {
            // Close the browser
            driver.quit();
        }
    }

    public static void main(String[] args) {
        // Define the URLs to open in parallel
        String[] urls = {
            "https://www.ajio.com",
            "https://www.flipkart.com",
            "https://www.amazon.in",
            "https://www.myntra.com"
        };

        // Create and start threads for each URL
        Thread[] threads = new Thread[urls.length];
        for (int i = 0; i < urls.length; i++) {
            threads[i] = new Thread(new ParallelTest(urls[i]), "Thread-" + (i + 1));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads have completed execution.");
    }
}

