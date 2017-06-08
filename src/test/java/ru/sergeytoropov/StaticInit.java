package ru.sergeytoropov;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
public class StaticInit {
    protected static final WebDriver driver;

    static {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void destroy() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Используется чтобы визуально наблюдать какие действия браузер выполняет в текущий момент.
     */
    public static void sleep() {
        sleep(3000);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
    }
}

