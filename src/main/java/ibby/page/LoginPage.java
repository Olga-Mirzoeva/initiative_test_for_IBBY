package ibby.page;

import ibby.core.initializer.SystemProperties;
import ibby.core.ui.browser.Browser;
import ibby.core.ui.element.Button;
import ibby.core.ui.element.Input;
import ibby.core.ui.element.Text;

import static ibby.core.ui.element.LocatorType.XPATH;

public class LoginPage extends BasePage {

    //LOCATORS
    private static final String NAME_SITE_TEXT_XPATH = "//*[@alt='Alibaba.com']";
    private static final String SEARCH_INPUT_XPATH = "//*[@name='SearchText']";
    private static final String SEARCH_BUTTON_XPATH = "//*[@value='Search']";
    private static final String NAME_CAR_TEXT_XPATH = "//*[text()='Sedan']";

    //ELEMENTS
    private Text nameSiteText = new Text(NAME_SITE_TEXT_XPATH, XPATH);
    private Input searchInput = new Input(SEARCH_INPUT_XPATH, XPATH);
    private Button searchButton = new Button(SEARCH_BUTTON_XPATH, XPATH);
    private Text nameSearchText = new Text(NAME_CAR_TEXT_XPATH, XPATH);

    public LoginPage() {
        super();
    }

    public LoginPage open() {
        Browser.getBrowser().open(System.getProperty(SystemProperties.URL.getSystemName()));
        return new LoginPage();
    }

    public String getTextNameSite() {
        return nameSiteText.getText();
    }

    public LoginPage typeSearch(String nameSearch) {
        searchInput.type(nameSearch);
        return this;
    }

    public LoginPage clickSearch() {
        searchButton.click();
        return new LoginPage();
    }

    public String getTextNameSearch() {
        return nameSearchText.getText();
    }

}
