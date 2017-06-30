package ru.sergeytoropov.scripts.litecart;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.litecart.MainPage;
import ru.sergeytoropov.litecart.ProductPage;
import ru.sergeytoropov.litecart.info.Product;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 30.06.17
 */
public class TestProductInfo extends Init {
    private MainPage mainPage;

    @Before
    public void init() {
        mainPage = new MainPage(driver);
    }

    @Test
    public void testProduct() {
        Product campaignsProduct = mainPage.getFirstCampaignsProductInfo();
        ProductPage productPage = mainPage.openFirstCampaignsProduct();
        Product product = productPage.getProductInfo();

        assertTrue(campaignsProduct.equals(product));
    }
}
