package ru.sergeytoropov.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import ru.sergeytoropov.LiteCart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergeytoropov
 * @since 06.06.17
 */
public class NavigationMenu extends LiteCart {

    public NavigationMenu(final WebDriver webDriver) {
        this(webDriver, true);
    }

    public NavigationMenu(final WebDriver webDriver, boolean isLogin) {
        super(webDriver);
        if (isLogin) {
            login();
        }
    }

    public static class MenuItem {
        public final int parent;
        public final int child;

        public MenuItem(final int parent, final int child) {
            this.parent = parent;
            this.child = child;
        }

        @Override
        public String toString() {
            return "MenuItem positon {" + "parent=" + parent + ", child=" + child + '}';
        }
    }

    // Список элементов верхнего уровня
    public final static String ALL_TOP_MENU_ITEMS = "ul#box-apps-menu > li#app-";

    // Выбрать элемент меню верхнего уровня
    public final static String CHOOSE_TOP_MENU_ITEM = "ul#box-apps-menu > li:nth-of-type(%s)";

    // Активный элемент меню верхнего уровня
    public final static String ACTIVE_TOP_MENU_ITEM = "ul#box-apps-menu > li.selected";

    // Название элемента меню верхнего уровня
    public final static String NAME_TOP_MENU_ITEM = "ul#box-apps-menu > li.selected > a > span.name";


    // Список элементов подменю верхнего уровня
    public static final String ALL_SUB_MENU_ITEMS = "ul#box-apps-menu > li.selected > ul.docs > li";

    // Выбрать элемент подменю верхнего уровня
    public final static String CHOOSE_SUB_MENU_ITEM = "ul#box-apps-menu > li.selected > ul.docs > li:nth-of-type(%s)";

    // Активный элемент подменю верхнего уровня
    public final static String ACTIVE_SUB_MENU_ITEM = "ul#box-apps-menu > li.selected > ul.docs > li.selected";

    // Название элемента подменю верхнего уровня
    public final static String NAME_SUB_MENU_ITEM = "ul#box-apps-menu > li.selected > ul.docs > li.selected span.name";


    // Заголовок
    public final static String HEADER = "td#content > h1";


    public List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        int parents = getDriver().findElements(By.cssSelector(ALL_TOP_MENU_ITEMS)).size();
        for (int cParent = 1; cParent <= parents; cParent++) {
            getDriver().findElement(By.cssSelector(String.format(CHOOSE_TOP_MENU_ITEM, cParent))).click();

            int children = getDriver().findElements(By.cssSelector(ALL_SUB_MENU_ITEMS)).size();
            for (int cChild = 1; cChild <= children; cChild++) {
                menuItems.add(new MenuItem(cParent, cChild));
            }

            if (children == 0) {
                menuItems.add(new MenuItem(cParent, 0));
            }
        }
        return menuItems;
    }

    public void chooseMenuItem(final MenuItem menuItem) {
        getDriver().findElement(By.cssSelector(String.format(CHOOSE_TOP_MENU_ITEM, menuItem.parent))).click();
        if (menuItem.child > 0) {
            getDriver().findElement(By.cssSelector(String.format(CHOOSE_SUB_MENU_ITEM, menuItem.child))).click();
        }
    }

    private String menuName(final String location) {
        try {
            return getDriver().findElement(By.cssSelector(location)).getText();
        } catch (NoSuchElementException nsee) {
            return "";
        }
    }

    public String getMenuName() {
        return menuName(NAME_TOP_MENU_ITEM);
    }

    public String getSubMenuName() {
        return menuName(NAME_SUB_MENU_ITEM);
    }

    public String getActiveMenuItemName() {
        return getMenuName() + " -> " + getSubMenuName();
    }

    public String getHeader() {
        return getDriver().findElement(By.cssSelector(HEADER)).getText();
    }
}
