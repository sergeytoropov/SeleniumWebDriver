package ru.sergeytoropov.litecart.elements;

import org.openqa.selenium.WebDriver;

/**
 * @author sergeytoropov
 * @since 22.06.17
 */
public class CheckoutElement extends Element {
    public CheckoutElement(WebDriver driver) {
        super(driver);
        xpath = "//div[@id='cart']//a[contains(text(), 'Checkout »')]";
    }
}
