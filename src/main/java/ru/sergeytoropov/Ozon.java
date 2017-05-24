package ru.sergeytoropov;

import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

/**
 * @author sergeytoropov
 * @since 24.05.17
 */

public class Ozon {
    private final WebDriver driver;

    public Ozon(final WebDriver driver) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver не проинициализирован.");
        }
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void open() {
        driver.get("https://ozon.ru/");
    }
}
