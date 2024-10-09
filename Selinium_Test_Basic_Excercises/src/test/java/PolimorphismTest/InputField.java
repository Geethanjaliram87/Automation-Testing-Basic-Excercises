package PolimorphismTest;
import org.openqa.selenium.WebElement;

public class InputField extends Element {
    
    public InputField(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(String keys) {
        webElement.sendKeys(keys);
    }
}
