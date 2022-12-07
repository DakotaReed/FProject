package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PricingPage {

    @FindBy(css = "a[data-tab-id='products']")
    public WebElement link_ProductsAndPlans;

    @FindBy(css = "a[data-tab-id='bundle']")
    public WebElement link_Bundles;

}
