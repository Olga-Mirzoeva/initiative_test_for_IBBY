package ibby.core.ui.element;

import ibby.core.ui.browser.Browser;

public class Button extends BaseElement {
    public Button(String locator, LocatorType locatorType) {
        super(locator, locatorType);
    }

    public void click() {
        Browser.getBrowser().click(by);
    }
}
