package ibby.uitest;

import ibby.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlibabaTest extends BaseTest {
    @Test(testName = "testAlibaba",
            description = "testAlibaba - Test to check the work of site alibaba")
    public void testAlibaba() {
        final String expectedNameSite = "Alibaba.com";
        final String nameSearch = "car";
        final String expectedNameSearch = "Sedan";

        LoginPage loginPage = new LoginPage();
        loginPage.open();
        String actualNameSite = loginPage.getTextNameSite();
        Assert.assertEquals(actualNameSite, expectedNameSite, "Verify name site");
        loginPage.typeSearch(nameSearch)
                .clickSearch();
        String actualNameSearch = loginPage.getTextNameSearch();
        Assert.assertEquals(actualNameSearch, expectedNameSearch, "Verify name search");
    }
}
