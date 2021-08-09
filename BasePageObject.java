package ExamenFinal.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    protected WebDriver chromeDriver;
    protected WebDriverWait explicitWait;


    public String getTituloPage() {
        return chromeDriver.getTitle();
    }

    public String getURLPage() {
        return chromeDriver.getCurrentUrl();
    }
}
