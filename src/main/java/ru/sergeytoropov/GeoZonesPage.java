package ru.sergeytoropov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
public class GeoZonesPage extends NavigationMenu {

    public GeoZonesPage(final WebDriver driver) {
        super(driver);
    }

    public void open() {
        // Позиция в меню (Возможно это не лучший вариант. Может следует искать позиция в меню по названию?)
        chooseMenuItem(new NavigationMenu.MenuItem(6, 0));
    }

    public static class GeoZone {
        public final int id;
        public final String name;
        public final int zones;
        public final String refEditZone;

        public GeoZone(int id, String name, int zones, String refEditZone) {
            this.id = id;
            this.name = name;
            this.zones = zones;
            this.refEditZone = refEditZone;
        }

        @Override
        public String toString() {
            return "GeoZone{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", zones=" + zones +
                    ", refEditZone='" + refEditZone + '\'' +
                    '}';
        }
    }

    public final List<GeoZone> geoZones = new ArrayList<>();

    private List<WebElement> getGeoZoneRows() {
        return driver.findElements(By.cssSelector("#content > form[name=geo_zones_form] > table > tbody > tr[class=row]"));
    }

    private void addGeoZone(WebElement country) {
        int id  = Integer.valueOf(country.findElement(By.cssSelector("td:nth-of-type(2)")).getText());
        String name = country.findElement(By.cssSelector("td:nth-of-type(3) > a")).getText();
        int zones = Integer.valueOf(country.findElement(By.cssSelector("td:nth-of-type(4)")).getText());
        String refEditZone= country.findElement(By.cssSelector("td:nth-of-type(3) > a")).getAttribute("href");

        geoZones.add(new GeoZone(id, name, zones, refEditZone));
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

    public List<EditGeoZonePage> getGeoZonesPages() {
        List<EditGeoZonePage> editGeoZonePages = new ArrayList<>();

        for (GeoZone geoZone: geoZones) {
            editGeoZonePages.add(new EditGeoZonePage(getDriver(), geoZone.refEditZone));
        }
        return editGeoZonePages;
    }
}
