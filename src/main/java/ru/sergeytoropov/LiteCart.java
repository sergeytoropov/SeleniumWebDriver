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
    private final WebDriver driver;

    public LiteCart(final WebDriver driver) {
        assert (driver != null);

        this.driver = driver;
        driver.get("http://localhost:8888/litecart/admin/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebElement loginForm() {
        //return driver.findElement(By.id("box-login"));
        return driver.findElement(By.xpath("//*[@id=\"box-login\"]"));
    }

    public void login() {
        loginForm().findElement(By.name("username")).sendKeys("admin");
        loginForm().findElement(By.name("password")).sendKeys("admin");
        loginForm().findElement(By.name("login")).click();

        /**
         * Вопрос!
         *
         * Почему когда я указыаю абсолютный путь до элемента он его не находит?
         * Элемент видимый я его вижу на странице.
         * Сверил путь (см. ниже) он правильный.
         * driver.findElement(By.xpath("//*[@id=\"box-login\"]")); - так находит элемент
         * driver.findElement(By.xpath("//*[@id=\"box-login\"]/form[@class=\"login-form\"]")); -а так нет.
         */
        //driver.findElement(By.xpath("//*[@id=\"box-login\"]/form[@class=\"login-form\"]/div[1]/table/tbody/tr[1]/td[2]/span/input")).sendKeys("admin");
    }
}

