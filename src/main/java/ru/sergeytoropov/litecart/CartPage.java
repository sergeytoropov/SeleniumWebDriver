package ru.sergeytoropov.litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public boolean isEmpty() {
        try {
            WebElement element = driver.findElement(By.xpath("//div[@id='checkout-cart-wrapper']/p/em[contains(text(), 'There are no items in your cart.')]"));
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;
    }


    public WebElement getElementRemoveButton(String productName) {
        return driver.findElement(By.xpath(
                "//div[@id='box-checkout-cart']//form[@name='cart_form']/div/p/a/strong[contains(text(), '" + productName + "')]/../../..//button[@name='remove_cart_item']"));
    }

    public List<WebElement> getElementColumnProductInTable() {
        return driver.findElements(By.xpath("//div[@id='order_confirmation-wrapper']/table/tbody/tr[not(@class='header') and not(@class='footer')]/td[@class='item']"));
    }

    //
    // Действия
    //

    public void removeAll() {
        List<WebElement> productColumn;
        while ((productColumn = getElementColumnProductInTable()).size() > 0) {
            WebElement product = productColumn.get(0);
            WebElement button = getElementRemoveButton(product.getText());
            wait.until(ExpectedConditions.visibilityOf(button));
            button.click();
            wait.until(ExpectedConditions.stalenessOf(product));

            // Чтобы не было подвисаний при поиске. Все равно уже продуктов больше нет.
            if (productColumn.size() == 1) {
                break;
            }
        }
    }
}
