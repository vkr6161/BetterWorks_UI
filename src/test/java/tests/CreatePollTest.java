package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreatePollTest extends BaseTest {

    @BeforeClass
    public void pollsPageSetUp() {
        pollsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void createPoll() {
        pollsPage.createPoll("This is for QA5.15");
    }

    @Test(priority = 2)
    public void validateSuccessCreatedPoll() {
        System.out.println("First Poll is " + pollsPage.getSizeOfPolls());
        Assert.assertEquals(pollsPage.getSizeOfPolls(), "This is for QA5.123333");//Asserting the created poll and expected poll
    }

}
