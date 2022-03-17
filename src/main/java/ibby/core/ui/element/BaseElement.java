package ibby.core.ui.element;

import ibby.core.ui.browser.Browser;
import org.openqa.selenium.By;

public abstract class BaseElement {
    protected By by;

    public BaseElement(String locator, LocatorType locatorType) {
        switch (locatorType) {
            case CSS:
                by = By.cssSelector(locator);
                break;
            case XPATH:
                by = By.xpath(locator);
                break;
            default:
                throw new RuntimeException("Not implemented for " + locatorType);
        }
    }

    public String getText() {
        return Browser.getBrowser().getText(by);
    }
}
