package ru.sergeytoropov.scripts.litecart;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.litecart.CartPage;
import ru.sergeytoropov.litecart.MainPage;
import ru.sergeytoropov.litecart.ProductPage;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
public class AddDeleteProductsInCartScript extends Init {
    private MainPage mainPage;

    @Before
    public void init() {
        mainPage = new MainPage(driver);
    }

    @Test
    public void DO() {
        MainPage homePage = mainPage;
        for (int count = 0; count < 3; count++) {
            ProductPage productPage = homePage.openFirstMostPopularProduct();
            productPage.addProductInCart();
            homePage = productPage.openMainPage();
        }
        CartPage cartPage = homePage.openCartPage();
        cartPage.removeAll();

        assertTrue(cartPage.isEmpty());
    }
}
