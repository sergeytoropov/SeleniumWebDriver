package ru.sergeytoropov.litecart.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
public abstract class Element {
    protected final WebDriver driver;
    protected String xpath;

    public WebDriver getDriver() {
        return driver;
    }

    public Element(final WebDriver driver) {
        assert (driver != null);
        this.driver = driver;
    }

    public void click() {
        driver.findElement(By.xpath(xpath)).click();
    }
}
