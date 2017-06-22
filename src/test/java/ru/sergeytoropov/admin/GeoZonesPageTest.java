package ru.sergeytoropov.admin;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.admin.GeoZonesPage;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
public class GeoZonesPageTest extends Init {
    private GeoZonesPage geoZonesPage;

    @Before
    public void init() {
        geoZonesPage = new GeoZonesPage(driver);
    }

    @Test
    public void print() {
        geoZonesPage.open();
        geoZonesPage.read();
        geoZonesPage.print();
    }
}
