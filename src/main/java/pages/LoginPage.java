package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Constants;
import utils.ElementUtil;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    //1. By Locator / OR

    private By email = By.id("inputGroupField");
    private By sendVerificationButton = By.cssSelector(".ladda-label");
    private By verificationCode = By.xpath("//input[@name=\"verificationCode\"]");
    private By loginButton = By.cssSelector(".btn.btn-primary");
    private By name = By.cssSelector(".sidebar-company-header");

    //2.Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    //3. page actions/methods/libs:
    public PollsPage doLogin(String emailID, String password) {
        elementUtil.doSendKeys(email, emailID);
        elementUtil.doClick(sendVerificationButton);
        elementUtil.clickWhenReady(verificationCode, 5);
        elementUtil.doSendKeys(verificationCode, password);
        elementUtil.doClick(loginButton);
        return new PollsPage(driver);

    }

    public String getTitle() {
        return elementUtil.waitForPageTitlePresent(Constants.BEFORE_LOGIN_PAGE_TITLE, 5);
    }

    public String getName() {
        return elementUtil.doGetText(name);
    }


}
