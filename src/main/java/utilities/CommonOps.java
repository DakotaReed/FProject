package utilities;

import com.google.common.util.concurrent.Uninterruptibles;
import extensions.UIActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import workFlows.WebFlows;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

public class CommonOps extends Base {

    public static String getData(String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration/DataConfigHubSpot.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);        }
        catch(Exception e) {
            System.out.println("Exception in reading XML file: " + e);        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    public void initBrowser(String browserType) {
        if (browserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver();
        else if (browserType.equalsIgnoreCase("firefox"))
            driver = initFirefoxDriver();
        else
            throw new RuntimeException("Invalid browser type");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(getData("URL"));
        ManagePages.initHubSpot();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }
    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    @BeforeClass
    public void startSession() {

        if (getData("PlatformNameOfTest").equalsIgnoreCase("web"))
            initBrowser(getData("BrowserType"));
        else
            throw new RuntimeException("Invalid platform");

        softAssert = new SoftAssert();
        softAssertAxis = new SoftAssert();
        screen = new Screen();
        UIActions.click(hubSpot_Home.btn_declineCookies);
    }

    @AfterClass
    public void closeSession() {
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        try {
            MonteScreenRecorder.startRecord(method.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        driver.manage().window().maximize();
        driver.get(getData("URL"));

        wait.until(ExpectedConditions.visibilityOf(hubSpot_Home.div_iFrameContainer));
        int elementWidth = hubSpot_Home.div_iFrameContainer.getSize().getWidth();
        int elementHeight = hubSpot_Home.div_iFrameContainer.getSize().getHeight();
        if (elementWidth == 100 && elementHeight == 96)
        {}
        else if (elementWidth == 0 && elementHeight == 0)
        {}
        else if (elementWidth == 420 && elementHeight == 674) {
            driver.switchTo().frame(hubSpot_iFrame.iFrame_HubBotChat);
            WebFlows.closeChatIFrame(hubSpot_iFrame.btn_closeChatAfterAutoOpen);
            driver.switchTo().defaultContent();
        }
        else {
            driver.switchTo().frame(hubSpot_iFrame.iFrame_HubBotChat);
            WebFlows.closeChatIFrame(hubSpot_iFrame.btn_closeChatMiddleOpening);
            driver.switchTo().defaultContent();
        }
    }

}