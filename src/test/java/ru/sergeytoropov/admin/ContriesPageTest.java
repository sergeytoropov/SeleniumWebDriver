package ru.sergeytoropov.admin;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.admin.CountriesPage;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 07.06.17
 */
public class ContriesPageTest extends Init {
    private CountriesPage contriesPage;

    @Before
    public void init() {
        contriesPage = new CountriesPage(driver);
    }

    @Test
    public void alphabeticalOrder() {
        contriesPage.open();
        contriesPage.read();

        assertTrue("Страны расположены не в алфавитном порядке.", contriesPage.isAlphabeticalOrder());
    }
}