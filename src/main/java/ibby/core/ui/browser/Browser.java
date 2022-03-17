package ibby.core.ui.browser;

import ibby.core.initializer.SystemProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser implements WrapsDriver {
    public long timeout;
    private WebDriver driver;
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static Logger logger;

    @Override
    public WebDriver getWrappedDriver() {
        return driver;
    }

    private Browser() {
        logger = LogManager.getLogger();
        logger.debug("Creating new Browser()");
        driver = WebDriverFactory.getWebDriver();
        timeout = Long.parseLong(System.getProperty(SystemProperties.TIMEOUT.getSystemName())) * 3;
    }

    public synchronized static Browser getBrowser() {
        if (browser.get() == null) {
            browser.set(new Browser());
        }
        return browser.get();
    }

    public void open(String url) {
        logger.debug("Try to open url: " + url);
        driver.get(url);
        logger.debug("Page is opened");
    }

    public void close() {
        if (browser.get() != null) {
            logger.debug("Closing browser");
            browser.get().getWrappedDriver().quit();
            browser.set(null);
            logger.debug("Browser is closed");
        }
    }

    public WebElement findElement(By by) {
        logger.debug("Try to find element by: " + by);
        return driver.findElement(by);
    }

    public void click(By by) {
        logger.debug("Click by:" + by);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        ExpectedCondition<WebElement> condition =
                ExpectedConditions.elementToBeClickable(by);
        wait.until(condition)
                .click();
    }

    public String getText(By by) {
        logger.debug("Get text from element by: " + by);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        ExpectedCondition<WebElement> condition =
                ExpectedConditions.visibilityOfElementLocated(by);
        return wait.until(condition).getText();
    }
}
