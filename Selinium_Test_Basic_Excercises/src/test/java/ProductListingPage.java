

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.stream.Collectors;

public class ProductListingPage {
    private WebDriver driver;

    private By productNames = By.className("product-name"); // Adjust locator if needed

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                     .stream()
                     .map(element -> element.getText())
                     .collect(Collectors.toList());
    }
}
