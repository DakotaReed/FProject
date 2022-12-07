package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.*;

public class ManagePages extends Base {

    public static void initHubSpot() {
        hubSpot_iFrame = PageFactory.initElements(driver, IFramePage.class);
        hubSpot_Home = PageFactory.initElements(driver, HomePage.class);
        hubSpot_SectionSearch = PageFactory.initElements(driver, SectionSearchPage.class);
        hubSpot_AllProducts = PageFactory.initElements(driver, AllProductsPage.class);
        hubSpot_ResourcesMenu = PageFactory.initElements(driver, ResourcesPage.class);
        hubSpot_SoftWareMenu = PageFactory.initElements(driver, SoftWarePage.class);
        hubSpot_Pricing = PageFactory.initElements(driver, PricingPage.class);
    }

}
