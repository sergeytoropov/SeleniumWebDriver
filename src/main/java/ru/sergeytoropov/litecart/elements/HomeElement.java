package ru.sergeytoropov.litecart.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
public class HomeElement extends Element {
    public HomeElement(final WebDriver driver) {
        super(driver);
        xpath = "//nav[@id='site-menu']/ul/li[@class='general-0']/a";
    }
}
