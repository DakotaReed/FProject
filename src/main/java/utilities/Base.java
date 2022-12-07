package utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;
import pageObjects.*;


public class Base {

    protected static WebDriver driver;

    protected static WebDriverWait wait;

    protected static Actions actions;

    protected static SoftAssert softAssert;

    protected static SoftAssert softAssertAxis;

    protected static Screen screen;

    protected static int listSize;

    protected static WebElement lastPlatform;

    protected static String textOfElement;

    protected static int indexOfProduct;

    protected static String presentYear;


//--------Page Objects--------
    protected static IFramePage hubSpot_iFrame;

    protected static HomePage hubSpot_Home;

    protected static SectionSearchPage hubSpot_SectionSearch;

    protected static PricingPage hubSpot_Pricing;

    protected static AllProductsPage hubSpot_AllProducts;

    protected static ResourcesPage hubSpot_ResourcesMenu;

    protected static SoftWarePage hubSpot_SoftWareMenu;

}
