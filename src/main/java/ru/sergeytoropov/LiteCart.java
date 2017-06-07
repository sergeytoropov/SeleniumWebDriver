package ru.sergeytoropov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * @author sergeytoropov
 * @since 02.06.17
 */
public class LiteCart {
    protected final WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public LiteCart(final WebDriver driver) {
        assert (driver != null);

        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
}

