package ibby.core.ui.browser;

import ibby.core.initializer.SystemProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static ibby.core.initializer.SystemProperties.BROWSER_TYPE;

public final class WebDriverFactory {
    private static Logger logger;

    public static WebDriver getWebDriver() {
        logger = LogManager.getLogger();
        logger.debug("WebDriver initializing...");
        WebDriver driver;
        String property = System.getProperty(BROWSER_TYPE.getSystemName());
        logger.debug("System contains " + BROWSER_TYPE.name() + ": " + property);
        BrowserType browserType = BrowserType.valueOf(property.toUpperCase());
        switch (browserType) {
            case CHROME:
                logger.debug("Chrome driver initializing ...");
                System.setProperty("webdriver.gecko.driver", "D://Olga//chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                logger.debug("Firefox driver initializing ...");
                System.setProperty("webdriver.gecko.driver", "D://Olga//geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                RuntimeException exception = new RuntimeException("No supported for " + browserType);
                logger.fatal(exception);
                throw exception;
        }
        driver.manage().window().maximize();
        property = System.getProperty(SystemProperties.TIMEOUT.getSystemName());
        long timeout = Long.parseLong(property);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeout * 20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(timeout * 3, TimeUnit.SECONDS);
        logger.debug("Web driver has started and configured");
        return driver;
    }

    private WebDriverFactory() {
    }
}
