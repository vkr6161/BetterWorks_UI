package base;


import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import pages.PollsPage;

import java.util.Properties;

public class BaseTest {

    DriverFactory df;
    public Properties prop;
    WebDriver driver;
    public LoginPage loginPage;
    public PollsPage pollsPage;

    @BeforeTest
    public void setUp() {
        df = new DriverFactory();
        prop = df.init_prop();

        //String browserName = prop.getProperty("browser");
        driver = df.init_driver(prop);
        driver.get(prop.getProperty("url"));

        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
