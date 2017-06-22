package ru.sergeytoropov.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.sergeytoropov.exceptions.LiteCartException;
import ru.sergeytoropov.litecart.elements.CheckoutElement;
import ru.sergeytoropov.litecart.elements.HomeElement;

import java.util.List;

/**
 * @author sergeytoropov
 * @since 07.06.17
 */
public class MainPage extends LiteCart {
    public final HomeElement homeElement;
    private final CheckoutElement checkoutElement;

    public MainPage(final WebDriver driver) {
        super(driver);
        homeElement = new HomeElement(driver);
        checkoutElement = new CheckoutElement(driver);

        //driver.get("http://localhost:8888/litecart/");
    }

    public boolean isMainPage() {
        try {
            WebElement element = driver.findElement(By.xpath("//div[@id=\"box-logotypes\"]/div/ul/li/a/img[@title=\"ACME Corp.\"]"));
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;
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

    public WebElement getNewCustomersClickHere() {
        return driver.findElement(By.xpath("//div[@id=\"box-account-login\"]/div/form/table//a[contains(text(),'New customers click here')]"));
    }

    public CreateAccountPage openCreateAccountPage() {
        getNewCustomersClickHere().click();
        CreateAccountPage page = new CreateAccountPage(driver);
        if (page.isCreateAccountPage()) {
            return page;
        }
        throw new LiteCartException("Страница регистрации нового пользователя НЕ открыта");
    }

    public WebElement linkLogout() {
        return driver.findElement(By.xpath("//div[@id=\"box-account\"]//a[contains(text(), 'Logout')]"));
    }

    public boolean isLogin() {
        try {
            linkLogout();
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public void logout() {
        if (isLogin()) {
            linkLogout().click();
        }
    }

    public WebElement getElementForm() {
        return driver.findElement(By.xpath("//div[@id=\"box-account-login\"]/div/form"));
    }

    /**
     * Field name email
     */
    public WebElement getElementEmail() {
        return getElementForm().findElement(By.xpath("//input[@name=\"email\"]"));
    }

    public String getEmail() {
        return getElementEmail().getAttribute("value");
    }

    public void setEmail(String value) {
        getElementEmail().sendKeys(value);
    }

    /**
     * Field name password
     */
    public WebElement getElementPassword() {
        return getElementForm().findElement(By.xpath("//input[@name=\"password\"]"));
    }

    public String getPassword() {
        return getElementPassword().getAttribute("value");
    }

    public void setPassword(String value) {
        getElementPassword().sendKeys(value);
    }

    /**
     *  Button login
     */
    public WebElement getElementButtonLogin() {
        return getElementForm().findElement(By.xpath("//button[@name=\"login\"]"));
    }

    public void login(String email, String password) {
        if (isLogin()) {
            logout();
        }
        setEmail(email);
        setPassword(password);
        getElementButtonLogin().click();
    }

    public ProductPage openFirstMostPopularProduct() {
        for (WebElement element: getMostPopular()) {
            element.click();
            break;
        }
        ProductPage productPage = new ProductPage(driver);
        if (productPage.isProductPage()) {
            return productPage;
        }
        throw new LiteCartException("Страница с описанием продукта не открылась");
    }

    //
    // Действия
    //

    public CartPage openCartPage() {
        checkoutElement.click();
        CartPage cartPage = new CartPage(driver);
        if (cartPage.isCartPage()) {
            return cartPage;
        }
        throw new LiteCartException("Страница корзины выбранных товаров не открылась");
    }
}
