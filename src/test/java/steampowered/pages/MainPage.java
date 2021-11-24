package steampowered.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final By spnLanguage = By.xpath("//span[@class='pulldown global_action_link']");
    private final By aLanguages = By.xpath("//a[@class=\"popup_menu_item tight\"]");
    private final String aSection = "//a[@class= 'pulldown_desktop' and text()='%s']";
    private final String aSubSection = "//div[contains(@class, 'popup_menu_subheader popup_genre_expand_header')]/child::a[contains(text(), '%s')]";


    public void changeLanguage(String language) {
        driver.findElement(spnLanguage).click();
        List<WebElement> languages = driver.findElements(aLanguages);
        for (WebElement element : languages) {
            if (element.getText().contains(language)) {
                element.click();
                return;
            }
        }
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).build().perform();
    }


    public void navigateSection(String section, String subsection) {
        Actions action = new Actions(driver);
        action.moveToElement(findElementByName(aSection, section)).build().perform();
        new WebDriverWait(driver, 20).until(ExpectedConditions
                .elementToBeClickable(findElementByName(aSubSection, subsection)));
        action.moveToElement(findElementByName(aSubSection, subsection)).build().perform();
        findElementByName(aSubSection, subsection).click();


    }

}


