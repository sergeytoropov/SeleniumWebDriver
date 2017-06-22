package ru.sergeytoropov.admin;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sergeytoropov.admin.NavigationMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 06.06.17
 */
@RunWith(DataProviderRunner.class)
public class NavigationMenuDataProviderTest {
    private static final WebDriver driver;
    private static final NavigationMenu navMenu;

    static {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        navMenu = new NavigationMenu(driver);
    }

    @Test
    @UseDataProvider("dataProviderMenuItems")
    public void navigationMenu(NavigationMenu.MenuItem menuItem) {
        navMenu.chooseMenuItem(menuItem);

        assertTrue("Отсутствует заголовок на странице. Меню: " + navMenu.getActiveMenuItemName(), navMenu.getHeader().length() > 0);
    }

    @DataProvider
    public static List<List<Object>> dataProviderMenuItems() {
        List<List<Object>> data = new ArrayList<>();
        for (NavigationMenu.MenuItem menuItem: navMenu.getMenuItems()) {
            List<Object> element = new ArrayList<>();
            element.add(menuItem);
            data.add(element);
        }
        return data;
    }

    @AfterClass
    public static void destroy() {
        if (driver != null) {
            driver.quit();
        }
    }
}