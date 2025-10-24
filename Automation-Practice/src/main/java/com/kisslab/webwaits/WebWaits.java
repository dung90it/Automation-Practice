package com.kisslab.webwaits;

import com.kisslab.interfaces.WebElements;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.kisslab.utils.Constants.TIMEOUT_FINDS;
import static com.kisslab.utils.Constants.TIMEOUT_WAITS;

public class WebWaits implements WebElements {
    private final WebDriver webDriver;
    private final WebDriverWait driverWait;
    private final WebDriverWait findWaits;


    public WebWaits(WebDriver webDriver) {
        this.webDriver = webDriver;
        driverWait = new WebDriverWait(webDriver, Duration.ofSeconds(TIMEOUT_WAITS));
        findWaits = new WebDriverWait(webDriver, Duration.ofSeconds(TIMEOUT_FINDS));
    }

    @Override
    public void elementClick(By item) {
        try {
            WebElement element = driverWait.until(ExpectedConditions
                    .elementToBeClickable(item));
            if (element != null) {
                element.click();
            }

        } catch (TimeoutException timeoutException) {
            timeoutException.printStackTrace();
        }

    }

    @Override
    public void elementFill(By item, String value) {
        try {
            driverWait.until(ExpectedConditions
                    .visibilityOfElementLocated(item)).sendKeys(value);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void waitForPageLoaded() {
        try {
            driverWait.until(webDriver -> ((String) ((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")).equals("complete"));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sleepInSeconds(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getTextBy(By item) {
        try {
            return driverWait.until(ExpectedConditions
                    .visibilityOfElementLocated(item)).getText();
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            return "";
        }

    }

    @Override
    public String untilUrl(String mUrl) {
        try {
            driverWait.until(ExpectedConditions.urlContains(mUrl));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
        return getCurrentUrl();


    }

    @Override
    public void navigateTo(String mUrl) {
        if (webDriver != null) {
            webDriver.get(mUrl);
            waitForPageLoaded();
        }
    }

    @Override
    public String getCurrentUrl() {
        if (webDriver != null)
            return webDriver.getCurrentUrl();
        else
            return "";
    }

    @Override
    public WebElement findElement(By item) {
        try {
            return findWaits.until(ExpectedConditions
                    .elementToBeClickable(item));
        } catch (TimeoutException timeoutException) {
            timeoutException.printStackTrace();
            return null;
        }

    }

    @Override
    public void cleanUp() {
        if (webDriver != null)
            webDriver.manage().deleteAllCookies();

    }

    @Override
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();

    }

}
