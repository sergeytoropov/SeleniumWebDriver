package ru.sergeytoropov;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
public class EditGeoZonePage extends NavigationMenu {
    private final String refEditGeoZonePage;

    public EditGeoZonePage(final WebDriver driver, final String refEditGeoZonePage) {
        super(driver, false);
        this.refEditGeoZonePage = refEditGeoZonePage;
    }

    public void open() {
        driver.get(refEditGeoZonePage);
    }

    public static class GeoZone {
        public final String country;
        public final String zone;

        public GeoZone(String country, String zone) {
            this.country = country;
            this.zone = zone;
        }

        @Override
        public String toString() {
            return "GeoZone{" +
                    "country='" + country + '\'' +
                    ", zone='" + zone + '\'' +
                    '}';
        }
    }

    public final List<GeoZone> geoZones = new ArrayList<>();

    private List<WebElement> getGeoZoneRows() {
        return driver.findElements(By.cssSelector("#table-zones > tbody > tr:not([class=header])"));
    }

    private void addGeoZone(WebElement geoZone) {
        try {
            String country = geoZone.findElement(By.cssSelector("td:nth-of-type(2) > span > span.selection > span > span")).getText();
            WebElement eSelect = geoZone.findElement(By.cssSelector("td:nth-of-type(3) > select"));
            String zone = "";
            if (eSelect.getAttribute("disabled") == null) {
                zone = eSelect.findElement(By.cssSelector("option[selected=selected]")).getText();
            }

            geoZones.add(new GeoZone(country, zone));
        } catch (NoSuchElementException nsee) {
        }
    }

    public void read() {
        geoZones.clear();
        for (WebElement element: getGeoZoneRows()) {
            addGeoZone(element);
        }
    }

    public void print() {
        geoZones.stream().forEach(System.out::println);
    }

    public String[] getCounties() {
        String[] countries = new String[geoZones.size()];

        int index = 0;
        for (GeoZone geoZone: geoZones) {
            countries[index++] = geoZone.country;
        }
        return countries;
    }

    public Map<String, List<String>> getZones() {
        Map<String, List<String>> map = new HashMap<>();

        for (GeoZone geoZone: geoZones) {
            if (map.containsKey(geoZone.country)) {
                List<String> list = map.get(geoZone.country);
                list.add(geoZone.zone);
            } else {
                List<String> list = new ArrayList<>();
                list.add(geoZone.zone);
                map.put(geoZone.country, list);
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return "EditGeoZonePage{" +
                "refEditGeoZonePage='" + refEditGeoZonePage + '\'' +
                ", geoZones=" + geoZones +
                '}';
    }
}
