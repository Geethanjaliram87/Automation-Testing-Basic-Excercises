package PolimorphismTest;
import org.openqa.selenium.WebElement;

public abstract class Element {
    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public void click() {
        webElement.click();
    }

    public String getText() {
        return webElement.getText();
    }
}
