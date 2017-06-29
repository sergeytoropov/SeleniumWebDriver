package ru.sergeytoropov.admin;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sergeytoropov.exceptions.LiteCartException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sergeytoropov
 * @since 23.06.17
 */
public class NewCountryPage extends NavigationMenu {
    private final String handle;
    private WebDriverWait exteranlLoadWait;

    public NewCountryPage(WebDriver webDriver) {
        super(webDriver, false);
        handle = webDriver.getWindowHandle();
        exteranlLoadWait = new WebDriverWait(driver, 60);
        if (!isNewCountryPage()) {
            new LiteCartException("Текущий адресс url [" + webDriver.getCurrentUrl() + "] не соответствует текущему объекту.");
        }
    }

    public boolean isNewCountryPage() {
        try {
            WebElement element = driver.findElement(By.xpath("//td[@id='content']/h1[contains(text(), 'Add New Country')]"));
        } catch (NoSuchElementException nsee) {
            return false;
        }
        return true;
    }

    @FindBy(xpath = "//td[@id='content']//*[@class='fa fa-external-link']/..")
    List<WebElement> externalLinks;

    //
    // Действия
    //

    public void externalLinkIsLoaded(String href) {
        try {
            Set<String> handles = driver.getWindowHandles();
            String externalHandle = handles.stream().filter(h -> !h.equals(handle)).findFirst().orElse("");
            driver.switchTo().window(externalHandle);

            exteranlLoadWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body")));

            driver.close();
            driver.switchTo().window(handle);

        } catch (TimeoutException te) {
            System.out.println("Битая ссылка: " + href);
            throw new TimeoutException(te);
        }
    }

    public void openAllExternalLinks() {
        for (WebElement element: externalLinks) {
            element.click();
            externalLinkIsLoaded(element.getAttribute("href"));
        }
    }
}
