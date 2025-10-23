import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest {
    private WebDriver driver = null;

    public void setupDriver(String browserType) {
        System.out.println("Start Set Up Driver for Web Application");
        if (browserType.equalsIgnoreCase("chrome")) {
            // Setup ChromeDriver (Assuming ChromeDriver executable is in the system PATH)
            driver = new org.openqa.selenium.chrome.ChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            // Setup FirefoxDriver (Assuming GeckoDriver executable is in the system PATH)

            driver = new org.openqa.selenium.firefox.FirefoxDriver();
        } else {
            System.out.println("Unsupported browser type: " + browserType);
            return;
        }
        driver.manage().window().maximize();
        driver.get("https://preprod.fuelcloud.com/login");
        System.out.println("Web Application Launched Successfully");
    }
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("Web Driver quit successfully");
        }
    }
    @Test
    public void Open(){
        setupDriver("firefox");
        closeDriver();
        Assert.assertTrue(true);
    }


}
