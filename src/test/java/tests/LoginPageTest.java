package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PollsPage;
import utils.Constants;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void getTitleBeforeLogin() {
        System.out.println(loginPage.getTitle());
        Assert.assertEquals(loginPage.getTitle(), Constants.BEFORE_LOGIN_PAGE_TITLE);
        System.out.println("Title before login is : " + loginPage.getTitle());
    }

    @Test(priority = 2)
    public void validateLoginFlow() {
        pollsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
        Assert.assertEquals(pollsPage.getTitle(), Constants.AFTER_LOGIN_PAGE_TITLE);
        System.out.println("Title after login is : " + pollsPage.getTitle());
    }


}
