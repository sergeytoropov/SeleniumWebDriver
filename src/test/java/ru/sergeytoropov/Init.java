package ru.sergeytoropov;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * @author sergeytoropov
 * @since 02.06.17
 */
public class Init {
    protected final WebDriver driver;

    protected Init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void destroy() {
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
