package steampowered.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ActionPage extends Page {

    public ActionPage(WebDriver driver) {
        super(driver);
    }

    private final By divDiscount = By.xpath("//div[@class='capsule header']/following-sibling::div/descendant::div[@class=\"discount_pct\"]");
    private final String divMaxDiscount = "//div[@class='capsule header']/following-sibling::div/descendant::div[@class=\"discount_pct\" and text()='%s']";

    public void findDiscounts() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(divDiscount));
        List<WebElement> discounts = driver.findElements(divDiscount);

        int maxDiscount = parseDiscounts(discounts.get(0).getText());

        for (WebElement element : discounts) {
            if (parseDiscounts(element.getText()) > maxDiscount) {
                maxDiscount = parseDiscounts(element.getText());
            }
        }

        List<WebElement> maxDiscountsList = findElementsByName(divMaxDiscount, "-" + maxDiscount + "%");
        if(maxDiscountsList.size() == 1){
            maxDiscountsList.get(0).click();
        } else {
            int random = (int) (Math.random() * (maxDiscountsList.size()));
            maxDiscountsList.get(random).click();
        }
    }

    public int parseDiscounts(String text) {
        return Integer.parseInt(text.substring(1, text.indexOf("%")));
    }
}
