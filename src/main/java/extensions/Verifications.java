package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import utilities.CommonOps;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.*;

public class Verifications extends CommonOps {

    @Step("Verify x_Axis and y_Axis Elements: 'Search' (SoftAssertion)")
    public static void verifyX_axisY_AxisSearch(int x_axisOfElem, int expectedX_Axis, int y_axisOfElem, int expectedY_Axis) {
        softAssertAxis.assertEquals(x_axisOfElem, expectedX_Axis);
        softAssertAxis.assertEquals(y_axisOfElem, expectedY_Axis);
        softAssertAxis.assertAll();
    }

    @Step("Verify Search Header Title")
    public static void verifySearchHeaderTitle(String textForSearch, String actualTitle) {
        assertEquals(actualTitle, textForSearch);
    }

    @Step("Verify Amount of Search Results")
    public  static void verifyAmountOfSearchResults (int actualAmount, int expectedAmount_OR_NULL) {
        assertTrue(actualAmount == expectedAmount_OR_NULL || actualAmount == 0);
    }

    @Step("Verify Displaying and Enabling of Elements (SoftAssertion)")
    public static void verifyDisplAndEnablOfElements(List<WebElement> elements) {
        for (WebElement element : elements) {
            wait.until(ExpectedConditions.visibilityOf(element));
            softAssert.assertTrue(element.isEnabled());
            softAssert.assertTrue(element.isDisplayed());
            softAssert.assertAll();}
    }

    @Step("Verify Same y_Axis Elements")
    public static void verifySameY_axis(int y_axisOfElem1, int y_axisOfElem2_OR_expected) {
        assertEquals(y_axisOfElem1, y_axisOfElem2_OR_expected);
    }

    @Step("Verify Platform Name")
    public static void verifyPlatformName(String platformName, String expectedName) {
        assertTrue(platformName.contains(expectedName));
    }

    @Step("Verify Platform Area Text")
    public static void verifyPlatformText(String platformText, String expectedText) {
        assertEquals(platformText, expectedText);
    }

    @Step("Verify Image Element Visually")
    public static void verifyVisualImageElement(String expectedImageName) {
        wait.until(ExpectedConditions.visibilityOf(hubSpot_Home.btn_search));
        try {
            screen.find(getData("ImageRepo") + expectedImageName + ".png");
        } catch (FindFailed e) {
            System.out.println("Error comparing Image File: " + e);
            fail("Error comparing Image File: " + e);
        }
    }

    @Step("Verify Present Year Included in Copyright")
    public static void verifyPresentYearInCopyright(String textCopyrightRow, String presentYear) {
        assertTrue(textCopyrightRow.contains(presentYear));
    }
}
