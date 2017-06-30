package ru.sergeytoropov.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

/**
 * @author sergeytoropov
 * @since 30.06.17
 */
public class NewProductPage extends NavigationMenu {
    public NewProductPage(WebDriver webDriver) {
        super(webDriver, false);
        // Проверяем соответсвие url объекту
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@id='content']/h1[contains(text(), 'Add New Product')]")));
    }

    //
    // Tabs
    //

    @FindBy(xpath = "//td[@id='content']//a[@href='#tab-general']")
    WebElement tabGeneral;

    @FindBy(xpath = "//td[@id='content']//a[@href='#tab-information']")
    WebElement tabInformation;

    @FindBy(xpath = "//td[@id='content']//a[@href='#tab-prices']")
    WebElement tabPrices;

    //
    // Elements TabGeneral
    //

    @FindBy(xpath = "//div[@id='tab-general']//label[contains(text(), 'Enabled')]/input")
    WebElement radionEnabled;

    @FindBy(xpath = "//div[@id='tab-general']//input[@name='name[en]']")
    WebElement name;

    //
    // Elements TabInformation
    //

    @FindBy(xpath = "//div[@id='tab-information']//select[@name='manufacturer_id']")
    WebElement manufacturer;

    //
    // Elements TabPrices
    //

    @FindBy(xpath = "//div[@id='tab-prices']//input[@name='purchase_price']")
    WebElement purchasePrice;

    @FindBy(xpath = "//div[@id='tab-prices']//select[@name='purchase_price_currency_code']")
    WebElement purchasePriceCurrencyCode;

    //
    // Buttons
    //

    @FindBy(xpath = "//td[@id='content']//button[@name='save']")
    WebElement buttonSave;

    //
    // Действия
    //

    public void openTabGeneral() {
        tabGeneral.click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//td[@id='content']//div[@id='tab-general']"), "style", "display: block;"));
    }

    public void openTabInformation() {
        tabInformation.click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//td[@id='content']//div[@id='tab-information']"), "style", "display: block;"));
    }

    public void openTabPrices() {
        tabPrices.click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//td[@id='content']//div[@id='tab-prices']"), "style", "display: block;"));
    }

    public void add(String uniqName) {
        openTabGeneral();
        radionEnabled.click();
        name.sendKeys(uniqName);

        openTabInformation();
        Select select = new Select(manufacturer);
        select.selectByValue("1");

        openTabPrices();
        purchasePrice.clear();
        purchasePrice.sendKeys("" + Math.abs((new Random()).nextInt() % 1000));
        select = new Select(purchasePriceCurrencyCode);
        select.selectByValue("USD");
    }

    public CatalogPage save() {
        buttonSave.click();
        return new CatalogPage(driver, false);
    }
}
