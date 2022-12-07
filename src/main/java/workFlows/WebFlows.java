package workFlows;

import com.google.common.util.concurrent.Uninterruptibles;
import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebFlows extends CommonOps {

    @Step("Business Flow: Close ChatIFrame")
    public static void closeChatIFrame(WebElement closeButton) {
        UIActions.click(closeButton);
    }

    @Step("Business Flow: Search on Site")
    public static void searchOnSite(String textForSearch) {
        UIActions.click(hubSpot_Home.btn_search);
        UIActions.updateText(hubSpot_Home.txt_search, textForSearch);
        UIActions.sendKeyBoardEnter(hubSpot_Home.txt_search);
    }

    @Step("Business Flow: Search Com or Blog")
    public static void choiceSearchComOrBlog(WebElement element) {
        UIActions.click(element);
    }

    @Step("Business Flow: Mouse Hover")
    public static void mouseHover(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    @Step("Business Flow: Find All Links in Element")
    public static List<WebElement> findAllLinksInContainer(WebElement container) {
        return container.findElements(By.tagName("a"));
    }

    @Step("Business Flow: Overview of All Products")
    public static void overviewOfAllProducts() {
        mouseHover(hubSpot_Home.btn_SoftWare);
        UIActions.click(hubSpot_SoftWareMenu.link_overviewOfAll);
    }

    @Step("Business Flow: Looking For All Products(Platforms)")
    public static void lookingForProducts() {
        UIActions.click(hubSpot_AllProducts.btn_upOrDownArrow);
        Uninterruptibles.sleepUninterruptibly(300, TimeUnit.MILLISECONDS);
    }

    @Step("Business Flow: Find Last Platform")
    public static void findLastPlatform() {
        for (int i=0; i<hubSpot_AllProducts.list_allPlatforms.size(); i++) {
            lastPlatform = hubSpot_AllProducts.list_allPlatforms.get(hubSpot_AllProducts.list_allPlatforms.size() - 1);
        }
    }

    @Step("Business Flow: Choice Platform")
    public static void choicePlatform(String platformName) {
        for (int i=0; i<hubSpot_AllProducts.list_allPlatforms.size(); i++) {
            UIActions.getText(hubSpot_AllProducts.list_allPlatforms.get(i));
            if (textOfElement.equalsIgnoreCase(platformName)) {
                indexOfProduct = i;
                UIActions.click(hubSpot_AllProducts.list_allPlatforms.get(i));
                break;
            }
        }
    }

    @Step("Business Flow: Getting Area Text of Product")
    public static void getTextAreaOfProduct(int indexOfProduct) {
        for (int i=0; i<hubSpot_AllProducts.list_areasTextOfProduct.size(); i++) {
            UIActions.getText(hubSpot_AllProducts.list_areasTextOfProduct.get(indexOfProduct));
            break; }
    }

}
