package steampowered.pages;

import framework.Browser;
import framework.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class GamePage extends Page {
    public GamePage(WebDriver driver) {
        super(driver);
    }

    private final By divAgeValidation = By.xpath("//div[text()='Please enter your birth date to continue:']");
    private final By sltAgeYear = By.xpath("//select[@name='ageYear']");
    private final By aViewPage = By.xpath("//span[text()='View Page']/..");
    private final By divGameName = By.xpath("//div[@id='appHubAppName']");
    private final By aInstallBtn = By.xpath("//a[@class='about_install_steam_link']");
    private final String aButton = "//a[contains(text(),'%s')]";

    public String getGameName(String year) {
        if (isElementPresentedOnPage(divAgeValidation)) {
            Select select = new Select(driver.findElement(sltAgeYear));
            select.selectByVisibleText(year);
            driver.findElement(aViewPage).click();
        }
        return driver.findElement(divGameName).getText();
    }

    public void installGame(String installBtn) {
        findElementByName(aButton, installBtn).click();
        driver.findElement(aInstallBtn).click();
    }

    public boolean isDownloadsExists() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        String downloadPath = "";
        if (Browser.getInstance().getDriver().toString().contains("ChromeDriver")) {
            ConfigLoader.getProperty("downloadPathForChrome");
        }
        if (Browser.getInstance().getDriver().toString().contains("FirefoxDriver")) {
            downloadPath = ConfigLoader.getProperty("downloadPathForFirefox");
        }
        File file = new File(downloadPath + ConfigLoader.getProperty("downloadedFile"));
        wait.until(driver -> file.exists());
        return file.exists();
    }

    public boolean isElementPresentedOnPage(By locator) {
        boolean IsElementDisplayed = true;
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            IsElementDisplayed = false;
        }
        return IsElementDisplayed;
    }

}
