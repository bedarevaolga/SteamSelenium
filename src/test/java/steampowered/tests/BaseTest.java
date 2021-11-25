package steampowered.tests;

import framework.Browser;
import framework.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steampowered.pages.ActionPage;
import steampowered.pages.GamePage;
import steampowered.pages.MainPage;

public class BaseTest {

    private static Browser currentBrowser ;
    public static MainPage mainPage;
    public static ActionPage actionPage;
    public static GamePage gamePage;


    @BeforeClass
    @Parameters({"browser"})
    public static void testSetup(String browser) {
        currentBrowser = Browser.getInstance(browser);
      currentBrowser.navigate(ConfigLoader.getProperty("url"));
    }

    @Test
    @Parameters({"browser","language", "year", "gameName"})
    public void testChooseGameWithMaxDiscount(String browser, String language, String year, String gameName) {

        mainPage = new MainPage(currentBrowser.getDriver());
        mainPage.changeLanguage(language);
        mainPage.navigateSection("Categories", "Action");
        actionPage = new ActionPage(currentBrowser.getDriver());
        actionPage.choseGameWithMaxDiscount();
        gamePage = new GamePage(currentBrowser.getDriver());
        Assert.assertEquals(gamePage.getGameName(year), gameName);
        gamePage.installGame("Install Steam");
        Assert.assertTrue(gamePage.isDownloadsExists());
    }

    @AfterClass
    public void closeBrowser() {
         currentBrowser.teardown();
    }


}
