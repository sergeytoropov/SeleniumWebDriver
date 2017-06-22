package ru.sergeytoropov.litecart;

import org.junit.Test;
import ru.sergeytoropov.StaticInit;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
public class ProductPageTest extends StaticInit {
    private static ProductPage productPage;

    static {
        productPage = (new MainPage(driver)).openFirstMostPopularProduct();
    }

    @Test
    public void getBuyNowForm() {
        productPage.getBuyNowForm();
    }
}