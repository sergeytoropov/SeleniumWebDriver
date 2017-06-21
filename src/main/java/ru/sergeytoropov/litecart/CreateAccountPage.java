package ru.sergeytoropov.litecart;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import ru.sergeytoropov.exceptions.LiteCartException;

/**
 * @author sergeytoropov
 * @since 20.06.17
 */
public class CreateAccountPage extends LiteCart {
    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCreateAccountPage() {
        try {
            WebElement element = driver.findElement(By.xpath("//div[@id=\"create-account\"]/h1[contains(text(), 'Create Account')]"));
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;
    }

    public WebElement getElementForm() {
        return driver.findElement(By.xpath("//div[@id=\"create-account\"]/div/form[@name=\"customer_form\"]"));
    }

    public WebElement getElementInput(String xpath) {
        return getElementForm().findElement(By.xpath(xpath));
    }

    /**
     * Field name Tax Id
     */
    public WebElement getElementTaxId() {
        return getElementInput("//input[@name=\"tax_id\"]");
    }

    public String getTaxId() {
        return getElementTaxId().getAttribute("value");
    }

    public void setTaxId(String value) {
        getElementTaxId().sendKeys(value);
    }

    /**
     *  Field name Company
     */
    public WebElement getElementCompany() {
        return getElementInput("//input[@name=\"company\"]");
    }

    public String getCompany() {
        return getElementCompany().getAttribute("value");
    }

    public void setCompany(String value) {
        getElementCompany().sendKeys(value);
    }

    /**
     * Field name First Name
     */
    public WebElement getElementFirstName() {
        return getElementInput("//input[@name=\"firstname\"]");
    }

    public String getFirstName() {
        return getElementFirstName().getAttribute("value");
    }

    public void setFirstName(String value) {
        getElementFirstName().sendKeys(value);
    }

    /**
     * Field name Last Name
     */
    public WebElement getElementLastName() {
        return getElementInput("//input[@name=\"lastname\"]");
    }

    public String getLastName() {
        return getElementLastName().getAttribute("value");
    }

    public void setLastName(String value) {
        getElementLastName().sendKeys(value);
    }

    /**
     * Field name address1
     */
    public WebElement getElementAddress1() {
        return getElementInput("//input[@name=\"address1\"]");
    }

    public String getAddress1() {
        return getElementAddress1().getAttribute("value");
    }

    public void setAddress1(String value) {
        getElementAddress1().sendKeys(value);
    }

    /**
     * Field name address2
     */
    public WebElement getElementAddress2() {
        return getElementInput("//input[@name=\"address2\"]");
    }

    public String getAddress2() {
        return getElementAddress2().getAttribute("value");
    }

    public void setAddress2(String value) {
        getElementAddress2().sendKeys(value);
    }

    /**
     * Field name postcode
     */
    public WebElement getElementPostCode() {
        return getElementInput("//input[@name=\"postcode\"]");
    }

    public String getPostCode() {
        return getElementPostCode().getAttribute("value");
    }

    public void setPostCode(String value) {
        getElementPostCode().sendKeys(value);
    }

    /**
     * Field name city
     */
    public WebElement getElementCity() {
        return getElementInput("//input[@name=\"city\"]");
    }

    public String getCity() {
        return getElementCity().getAttribute("value");
    }

    public void setCity(String value) {
        getElementCity().sendKeys(value);
    }

    /**
     * Field name country
     */
    public void setCountry() {
        // "$('select[name=\"country_code\"]').val(\"SO\").trigger(\"change\");"
        ((JavascriptExecutor) driver).executeScript("$('select[name=\"country_code\"]').select2(\"val\", \"US\");");
    }

    /**
     * Field name zone code
     */
    public WebElement getElementZoneCode() {
        return getElementInput("//select[@name=\"zone_code\"]");
    }

    public void setZoneCode(String name) {
        // Мы работаем только с Unated States
        setCountry();

        Select select = new Select(getElementZoneCode());
        select.selectByVisibleText(name);
    }

    public String getZoneCode() {
        return (new Select(getElementZoneCode())).getFirstSelectedOption().getText();
    }

    /**
     * Field name email
     */
    public WebElement getElementEmail() {
        return getElementInput("//input[@name=\"email\"]");
    }

    public String getEmail() {
        return getElementEmail().getAttribute("value");
    }

    public void setEmail(String value) {
        getElementEmail().sendKeys(value);
    }

    /**
     * Field name phone
     */
    public WebElement getElementPhone() {
        return getElementInput("//input[@name=\"phone\"]");
    }

    public String getPhone() {
        return getElementPhone().getAttribute("value");
    }

    public void setPhone(String value) {
        String prefix = getElementPhone().getAttribute("placeholder");
        getElementPhone().sendKeys(prefix + value);
    }

    /**
     * Field name password
     */
    public WebElement getElementPassword() {
        return getElementInput("//input[@name=\"password\"]");
    }

    public String getPassword() {
        return getElementPassword().getAttribute("value");
    }

    public void setPassword(String value) {
        getElementPassword().sendKeys(value);
    }

    /**
     * Field name password
     */
    public WebElement getElementConfirmPassword() {
        return getElementInput("//input[@name=\"confirmed_password\"]");
    }

    public String getConfirmPassword() {
        return getElementConfirmPassword().getAttribute("value");
    }

    public void setConfirmPassword(String value) {
        getElementConfirmPassword().sendKeys(value);
    }

    /**
     * Button create account
     */
    public WebElement buttonCreateAccount() {
        return getElementInput("//button[@name=\"create_account\"]");
    }

    public MainPage createAccount() {
        buttonCreateAccount().click();
        MainPage mainPage = new MainPage(driver);
        if (mainPage.isMainPage()) {
            return mainPage;
        }
        throw new LiteCartException("Не удалось создать нового пользователя");
    }
}
