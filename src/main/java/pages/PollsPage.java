package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.Constants;
import utils.ElementUtil;

import java.awt.*;
import java.util.List;

public class PollsPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public PollsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }


    private By createPostButton = By.id("create-button");
    private By selectRadioBox = By.xpath("//span[contains(text(), 'Official')]/parent::*/span/input");
    private By openButton = By.xpath("//button[contains(text(), 'OPEN') and @class='hyphenButton poll-btn btn btn-default']");
    private By selectBox = By.xpath("//span[@class=\"Select-arrow-zone\"]");
    private By selectButton = By.cssSelector(".btn.btn-default");
    private By textArea = By.id("textAreaField");
    private By publishButton = By.cssSelector(".smallButton.btn.btn-default");
    private By submitButton = By.xpath("//button[contains(text(), 'Submit')]");
    private By commentArea = By.cssSelector(".tilesMargin.commentsPanelBody");
    private By totalPollsQuestion = By.cssSelector(".pollDiv.col-xs-11 div span span:nth-child(1)");


    public void createPoll(String pollText) {

        elementUtil.clickWhenReady(createPostButton, 10);
        elementUtil.doClick(selectRadioBox);
        elementUtil.doClick(openButton);

        elementUtil.waitForElementWithFluentWait(selectBox,15,5);
        elementUtil.doClick(selectBox);
        Actions keyDown1 = new Actions(driver);
        keyDown1.sendKeys(Keys.chord(Keys.DOWN, Keys.DOWN, Keys.ENTER)).perform();
        elementUtil.doClick(selectButton);
        elementUtil.doSendKeys(textArea, pollText);
        elementUtil.doClick(publishButton);
        elementUtil.waitForElementWithFluentWait(commentArea, 10, 5);


    }


    public String getSizeOfPolls() {
        String question = "";
        List<WebElement> firstQuestion = elementUtil.getElements(totalPollsQuestion);
        for (WebElement e : firstQuestion) {
            question = e.getText();
            break;


        }
        return question;
    }

    public String getTitle() {
        return elementUtil.waitForPageTitlePresent(Constants.AFTER_LOGIN_PAGE_TITLE, 5);
    }
}
