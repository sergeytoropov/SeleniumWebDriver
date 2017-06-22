package ru.sergeytoropov.admin;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.sergeytoropov.StaticInit;
import ru.sergeytoropov.admin.EditGeoZonePage;
import ru.sergeytoropov.admin.GeoZonesPage;
import ru.sergeytoropov.utils.AlphabeticalOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
@RunWith(DataProviderRunner.class)
public class EditGeoZonePageTest extends StaticInit {
    private static GeoZonesPage geoZonesPage;

    static {
        geoZonesPage = new GeoZonesPage(driver);
    }

    @Test
    @UseDataProvider("dataProviderEditGeoZones")
    public void alphabeticalOrder(EditGeoZonePage editGeoZonePage) {
        editGeoZonePage.open();
        editGeoZonePage.read();

        assertTrue("Страны не отсортированы.", AlphabeticalOrder.list(editGeoZonePage.getCounties()));

        Map<String, List<String>> zones = editGeoZonePage.getZones();
        for (String country: zones.keySet()) {
            assertTrue("В стране " + country + " не отсортированы зоны.", AlphabeticalOrder.list(zones.get(country)));
        }
    }

    @DataProvider
    public static List<List<Object>> dataProviderEditGeoZones() {
        List<List<Object>> data = new ArrayList<>();

        geoZonesPage.open();
        geoZonesPage.read();
        for (EditGeoZonePage page : geoZonesPage.getGeoZonesPages()) {
            List<Object> elements = new ArrayList<>();
            elements.add(page);
            data.add(elements);
        }
        return data;
    }
}