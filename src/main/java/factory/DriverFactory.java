package factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    WebDriver driver;
    Properties prop;
    public static String highlight;
    OptionsManager optionsManager;


    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();


    public WebDriver init_driver(Properties prop) {

        String browserName = prop.getProperty("browser");
        System.out.println("browser name is: " + browserName);

        highlight = prop.getProperty("highlight").trim();
        optionsManager = new OptionsManager(prop);

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            //driver = new ChromeDriver(optionsManager.getChromeOptions());
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
            tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else if (browserName.equals("safari")) {
            //driver = new SafariDriver();
            tlDriver.set(new SafariDriver());
        } else {
            System.out.println("Please pass the correct browser name : " + browserName);
        }

        getDriver().manage().window().fullscreen();
        getDriver().manage().deleteAllCookies();

        return getDriver();

    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }


    /**
     * This method is used to initialize the properties from config file.
     *
     * @return returns Properties prop
     */
    public Properties init_prop() {

        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./resources/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}