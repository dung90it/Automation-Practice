package com.kisslab.interfaces;

import org.openqa.selenium.By;

public interface WebElements {
    void elementClick(By item);

    void elementFill(By item, String value);

    void waitForPageLoaded();

    void sleepInSeconds(int time);


}
