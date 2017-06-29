package ru.sergeytoropov.scripts.admin;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.admin.NewCountryPage;
import ru.sergeytoropov.admin.CountriesPage;

/**
 * @author sergeytoropov
 * @since 29.06.17
 */
public class CheckLinksOnCountryPage extends Init {
    private NewCountryPage newCountryPage;

    @Before
    public void init() {
        CountriesPage contriesPage = new CountriesPage(driver);
        contriesPage.open();
        newCountryPage = contriesPage.openNewCountryPage();
    }

    @Test
    public void checkLinks() {
        newCountryPage.openAllExternalLinks();
    }
}
