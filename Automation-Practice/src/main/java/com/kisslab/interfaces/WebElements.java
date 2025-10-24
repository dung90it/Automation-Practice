package com.kisslab.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebElements {
    void elementClick(By item);

    void elementFill(By item, String value);

    void waitForPageLoaded();

    void sleepInSeconds(int time);

    String getTextBy(By item);

    String untilUrl(String mUrl);

    void navigateTo(String mUrl);

    WebElement findElement(By item);

    void cleanUp();

    void tearDown();

    String getCurrentUrl();


}
