package ru.sergeytoropov;

import org.junit.Before;
import org.junit.Test;

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
