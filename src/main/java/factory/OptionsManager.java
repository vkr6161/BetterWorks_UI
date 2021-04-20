package factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless").trim()))
        {
            co.addArguments("--window-size=1920,1080");
            co.addArguments("--start-maximized");
            co.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) co.addArguments("--incognito");
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
        if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("--incognito");
        return fo;
    }
}
