package ru.sergeytoropov.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * @author sergeytoropov
 * @since 22.06.17
 */
public class CartPage extends LiteCart {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartPage() {
        try {
            WebElement element = driver.findElement(By.xpath("//div[@id='checkout-cart-wrapper']"));
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;
    }

    @FindBy(xpath = "//div[@id='checkout-cart-wrapper']/p/em[contains(text(), 'There are no items in your cart.')]")
    WebElement noItemsMessage;

    public boolean isEmpty() {
        try {
            return noItemsMessage.isDisplayed();
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }


    public WebElement getElementRemoveButton(String productName) {
        return driver.findElement(By.xpath(
                "//div[@id='box-checkout-cart']//form[@name='cart_form']/div/p/a/strong[contains(text(), '" + productName + "')]/../../..//button[@name='remove_cart_item']"));
    }

    @FindBy(xpath = "//div[@id='order_confirmation-wrapper']/table/tbody/tr[not(@class='header') and not(@class='footer')]/td[@class='item']")
    List<WebElement> columnProduct;

    //
    // Действия
    //

    public void removeAll() {
        /*
        // Внимание! следующий цикл работать не будет.
        // Возможно из-за, того что index < columnProduct.size() вычисляется каждый раз заново для каждой итерации цикла?
        for (int index = 0; index < columnProduct.size(); index++) {
            // ...
        }
        */

        // Так работает.
        int size = columnProduct.size();
        for (int index = 0; index < size;  index++) {
            WebElement product = columnProduct.get(0);
            WebElement button = getElementRemoveButton(product.getText());
            wait.until(ExpectedConditions.visibilityOf(button));
            button.click();
            wait.until(ExpectedConditions.stalenessOf(product));
        }
    }
}
