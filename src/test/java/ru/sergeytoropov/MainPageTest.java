package ru.sergeytoropov;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 07.06.17
 */
public class MainPageTest extends Init {
    private MainPage mainPage;

    @Before
    public void init() {
        mainPage= new MainPage(driver);
    }

    private final String message = "Товар на главной странице содержит ноль или более одного стикера.";

    @Test
    public void mostPopular() {
        for (WebElement element: mainPage.getMostPopular()) {
            assertTrue(message, mainPage.isOneSticker(mainPage.getSticker(element)));
        }
    }

    @Test
    public void campaigns() {
        for (WebElement element: mainPage.getCampaigns()) {
            assertTrue(message, mainPage.isOneSticker(mainPage.getSticker(element)));
        }
    }

    @Test
    public void latestProducts() {
        for (WebElement element: mainPage.getLatestProducts()) {
            assertTrue(message, mainPage.isOneSticker(mainPage.getSticker(element)));
        }
    }
}