package ru.sergeytoropov.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.sergeytoropov.LiteCart;
import ru.sergeytoropov.exceptions.LiteCartException;
import ru.sergeytoropov.litecart.elements.HomeElement;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
public class ProductPage extends LiteCart {
    public final HomeElement homeElement;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.homeElement = new HomeElement(driver);
    }

    public boolean isProductPage() {
        try {
            WebElement element = driver.findElement(By.xpath("//*[@id='box-product']//div[@class='information']"));
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;
    }

    public WebElement getBuyNowForm() {
        return driver.findElement(By.xpath("//form[@name='buy_now_form']"));
    }

    public boolean isSize() {
        for (WebElement element: getBuyNowForm().findElements(By.xpath("./table/tbody//td"))) {
            if ("options".equals(element.getAttribute("class"))) {
                return true;
            }
        }
        return false;
    }

    public final String[] sizeOptionValue = {"Small", "Medium", "Large"};

    public void setSize(String optionValue) {
        Select select = new Select(getBuyNowForm().findElement(By.xpath("./table/tbody//select[@name='options[Size]']")));
        select.selectByValue(optionValue);
    }

    public WebElement buttonAddToCart() {
        return getBuyNowForm().findElement(By.xpath("./table/tbody//button[@name='add_cart_product']"));
    }

    public WebElement getElementQuanityInCart() {
        return driver.findElement(By.xpath("//div[@id='cart']//span[@class='quantity']"));
    }

    @FindBy(xpath = "//div[@id='cart']//span[@class='quantity']")
    WebElement quantityInCart;

    public int getQuantityInCart() {
        return Integer.parseInt(quantityInCart.getText());
    }

    //
    // Действия
    //

    public void addProductInCart() {
        int currentQuantityInCart = getQuantityInCart();
        if (isSize()) {
            setSize(sizeOptionValue[0]);
        }
        buttonAddToCart().click();

        //TODO Заменить ExpectedConditions пока не понял как
        // Ждем десять секунд на изменения количества товаров в корзине
        for (int count = 0; count < 100; count++) {
            if (currentQuantityInCart!= getQuantityInCart()) {
                return;
            }
            sleep(100);
        }
    }

    public MainPage openMainPage() {
        homeElement.click();
        MainPage mainPage = new MainPage(driver);
        if (mainPage.isMainPage()) {
            return mainPage;
        }
        throw new LiteCartException("Домашняя страница приложения не открылась");
    }
}
