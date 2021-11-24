package steampowered.pages;

import framework.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class GamePage extends Page{
    public GamePage(WebDriver driver) {
        super(driver);
    }

    private final By divDiscount = By.xpath("//div[text()='Please enter your birth date to continue:']");
    private final By sltAgeYear= By.xpath("//select[@name='ageYear']");
    private final By aViewPage= By.xpath("//span[text()='View Page']/..");
    private final By divGameName= By.xpath("//div[@id='appHubAppName']");
    private final By aInstallBtn = By.xpath("//a[@class='about_install_steam_link']");
    private final String aButton = "//a[contains(text(),'%s')]";

    public String getGameName(String year) {
        if(driver.findElement(divDiscount).isDisplayed()){
            Select select = new Select(driver.findElement(sltAgeYear));
            select.selectByVisibleText(year);
            driver.findElement(aViewPage).click();
        }
        return driver.findElement(divGameName).getText();
    }
    public void installGame(String nameBtn) {
        findElementByName(aButton, nameBtn).click();
        driver.findElement(aInstallBtn).click();
    }

    public boolean isDownloadsExists( ) {

        WebDriverWait wait = new WebDriverWait(driver, 15);
        File file = new File(ConfigLoader.getProperty("downloadedFilePath"));
        System.out.println(file.length());
        System.out.println(file.getUsableSpace());
        System.out.println(file.getFreeSpace());
        wait.until(driver -> file.exists());
        return file.exists();
    }

}
