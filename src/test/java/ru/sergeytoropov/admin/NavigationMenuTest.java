package ru.sergeytoropov.admin;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 06.06.17
 */
public class NavigationMenuTest extends Init {
    private NavigationMenu navMenu;

    @Before
    public void init() {
        navMenu = new NavigationMenu(driver);
    }

    @Test
    public void navigationMenu() {
        for (NavigationMenu.MenuItem menuItem: navMenu.getMenuItems()) {
            navMenu.chooseMenuItem(menuItem);

            assertTrue("Отсутствует заголовок на странице. Меню: " + navMenu.getActiveMenuItemName(), navMenu.getHeader().length() > 0);
        }
    }
}