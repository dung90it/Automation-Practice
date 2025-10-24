package base;

import com.kisslab.testrail.APIException;
import com.kisslab.testrail.TestRailClient;
import com.kisslab.testrail.TestRailStatus;
import com.kisslab.utils.Constants;
import com.kisslab.webwaits.WebWaits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private final TestRailClient trClient = new TestRailClient();
    private String testRunId = "101";
    public WebWaits mWebWaits;

    @BeforeClass
    public void setUp() {
        WebDriver mWebDriver = getDriver(Constants.BROWSER_FIREFOX);
        mWebDriver.manage().window().maximize();
        mWebWaits = new WebWaits(mWebDriver);
    }


    @AfterMethod
    public void cleanUp(ITestResult result) {
        String description = result.getMethod().getDescription();
        if (description != null) {
            String[] arrayDes = description.split(":");
            if (arrayDes.length >= 2) {
                String caseID = arrayDes[0];
                String comment = arrayDes[1];
                TestRailStatus statusId = TestRailStatus.Failed;
                if (result.isSuccess()) {
                    statusId = TestRailStatus.Passed;
                }
                try {
                    trClient.addResultForCase(testRunId, caseID, statusId, comment);
                } catch (APIException | IOException apiException) {
                    apiException.printStackTrace();
                }

            }
        }
        mWebWaits.cleanUp();
    }

    @AfterClass
    private void tearDown() {
        mWebWaits.tearDown();
    }

    private WebDriver getDriver(String browser) {
        System.out.println("Get driver for: " + browser);
        if (browser.equalsIgnoreCase(Constants.BROWSER_CHROME)) {
            ChromeOptions chrome_options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            chrome_options.addArguments("--disable-blink-features=AutomationControlled");
            chrome_options.addArguments("--disable-infobars");
            chrome_options.addArguments("--disable-notifications");
            chrome_options.addArguments("--disable-save-password-bubble");
            chrome_options.addArguments("--disable-extensions");
            prefs.put("profile.default_content_setting_values.notifications", 2);
            chrome_options.setExperimentalOption("prefs", prefs);
            return new ChromeDriver(chrome_options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

}
