package ru.sergeytoropov.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @author sergeytoropov
 * @since 02.06.17
 */
public class LiteCart {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public WebDriver getDriver() {
        return driver;
    }

    public LiteCart(final WebDriver driver) {
        assert (driver != null);

        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        this.wait = new WebDriverWait(driver, 10);

        PageFactory.initElements(driver, this);
    }

    public WebElement loginForm() {
        return driver.findElement(By.id("box-login"));
    }

    public void login() {
        driver.get("http://localhost:8888/litecart/admin/");
        loginForm().findElement(By.name("username")).sendKeys("admin");
        loginForm().findElement(By.name("password")).sendKeys("admin");
        loginForm().findElement(By.name("login")).click();
    }

    public void sleep( int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }
    }
}

