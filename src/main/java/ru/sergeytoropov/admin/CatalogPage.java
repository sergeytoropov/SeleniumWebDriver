package ru.sergeytoropov.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * @author sergeytoropov
 * @since 30.06.17
 */
public class CatalogPage extends NavigationMenu {

    public CatalogPage(WebDriver webDriver, boolean isLogin) {
        super(webDriver, isLogin);
        if (isLogin) {
            chooseMenuItem(new MenuItem(2, 1));
        }
        // Проверяем соответсвие url объекту
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='content']/h1[contains(text(), 'Catalog')]")));
    }

    public CatalogPage(WebDriver webDriver) {
        this(webDriver, true);
    }

    @FindBy(xpath = "//td[@id='content']//a[@class='button' and contains(text(), 'Product')]")
    WebElement buttonAddNewProduct;

    //
    // Действия
    //

    public boolean find(String uniqName) {
        try {
            WebElement element = driver.findElement(By.xpath("//td[@id='content']//table[@class='dataTable']/tbody/tr[@class='row']//a[contains(text(), '" + uniqName + "')]"));
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public NewProductPage openAddNewProduct() {
        buttonAddNewProduct.click();
        return new NewProductPage(driver);
    }
}
