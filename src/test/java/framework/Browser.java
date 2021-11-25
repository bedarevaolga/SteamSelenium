package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class Browser {

    private static Browser instance;
    private static WebDriver driver;

    public static Browser getInstance(String browser) {
        if (instance == null) {
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.setUp(browser);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(ConfigLoader.getProperty("pageLoadTimeout")), TimeUnit.SECONDS)
                    .implicitlyWait(Integer.parseInt(ConfigLoader.getProperty("implicitlyWait")), TimeUnit.SECONDS)
                    .setScriptTimeout(Integer.parseInt(ConfigLoader.getProperty("setScriptTimeout")), TimeUnit.SECONDS);
            instance = new Browser();
        }
        return instance;
    }

    public void navigate(String url) {
        driver.get(url);
    }


    private Browser() {
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void teardown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
