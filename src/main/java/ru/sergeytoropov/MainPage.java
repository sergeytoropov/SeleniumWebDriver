package ru.sergeytoropov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author sergeytoropov
 * @since 07.06.17
 */
public class MainPage extends LiteCart {
    public MainPage(final WebDriver driver) {
        super(driver);
        driver.get("http://localhost:8888/litecart/");
    }

    public List<WebElement> getProducts(final String location) {
        return driver.findElements(By.cssSelector(location + " > div.content > ul.listing-wrapper > li"));
    }

    public List<WebElement> getMostPopular() {
        return getProducts("#box-most-popular");
    }

    public List<WebElement> getCampaigns() {
        return getProducts("#box-campaigns");
    }

    public List<WebElement> getLatestProducts() {
        return getProducts("#box-latest-products");
    }

    public List<WebElement> getSticker(WebElement element) {
        return element.findElements(By.cssSelector("a.link > div.image-wrapper > div.sticker"));
    }

    public boolean isOneSticker(List<WebElement> stickers) {
        return stickers.size() == 1;
    }
}
