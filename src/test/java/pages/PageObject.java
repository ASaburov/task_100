package pages;

import driver.Driver;
import org.openqa.selenium.WebDriver;

public class PageObject {
    private final String TITLE;
    WebDriver driver;

    PageObject (String title) {
        TITLE = title;
        driver = Driver.getInstance().getWebDriver();
    }

    public boolean isDisplayed() {
        return driver.getTitle().contentEquals(TITLE);
    }
}
