package ru.sergeytoropov;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 24.05.17
 */
public class OzonTest {
    private WebDriver driver;

    @Before
    public void init() {
        driver = new ChromeDriver();
    }

    @Test
    public void open() {
        Ozon ozon = new Ozon(driver);
        ozon.open();
    }

    @Test(expected = IllegalStateException.class)
    public void badOpen() {
        Ozon ozon = new Ozon(null);
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.quit();
        }
    }
}