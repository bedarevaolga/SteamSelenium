package steampowered.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Page {

    protected final WebDriver driver;

    public Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected WebElement findElementByName(String locator, String name) {
        return driver.findElement(By.xpath(String.format(locator, name)));
    }
    protected List<WebElement> findElementsByName(String locator, String name) {
        return driver.findElements(By.xpath(String.format(locator, name)));
    }

}

