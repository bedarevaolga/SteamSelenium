package steampowered.tests;

import framework.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steampowered.pages.ActionPage;
import steampowered.pages.GamePage;
import steampowered.pages.MainPage;

public class BaseTest {

    public static MainPage mainPage;
    public static ActionPage actionPage;
    public static GamePage gamePage;


    @BeforeClass
    @Parameters({"browser"})
    public static void testSetup(String browser) {
        Browser.getInstance().SetUp(browser);
    }

    @Test
    @Parameters({"language", "year", "gameName"})
    public void testChooseGameWithMaxDiscount(String language, String year, String gameName) {

        mainPage = new MainPage(Browser.getInstance().getDriver());
        mainPage.changeLanguage(language);
        mainPage.navigateSection("Categories", "Action");
        actionPage = new ActionPage(Browser.getInstance().getDriver());
        actionPage.findDiscounts();
        gamePage = new GamePage(Browser.getInstance().getDriver());
        Assert.assertEquals(gamePage.getGameName(year), gameName);
        gamePage.installGame("Install Steam");
        Assert.assertTrue(gamePage.isDownloadsExists());
    }

    @AfterClass
    public void closeBrowser() {
           Browser.getInstance().teardown();
    }


}
