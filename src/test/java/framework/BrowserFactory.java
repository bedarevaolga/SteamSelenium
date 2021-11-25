package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Locale;

public class BrowserFactory {


    private WebDriver driver = null;

    public WebDriver setUp(String browser) {

        switch (browser.toUpperCase(Locale.ROOT)) {
            case "CHROME" -> {
                ChromeOptions options = new ChromeOptions();
                HashMap<String, Object> chromePref = new HashMap<>();
                chromePref.put("safebrowsing.enabled", true);
                chromePref.put("download.default_directory", ConfigLoader.getProperty("downloadPathForChrome"));
                options.setExperimentalOption("prefs", chromePref);
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver(options);
            }
            case "FIREFOX" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.dir", ConfigLoader.getProperty("downloadPathForFirefox"));
                firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                firefoxOptions.setProfile(firefoxProfile);
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver(firefoxOptions);
            }
        }
       return driver;
    }
}
