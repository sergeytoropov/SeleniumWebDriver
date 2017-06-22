package ru.sergeytoropov.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sergeytoropov
 * @since 07.06.17
 */
public class EditCountryPage extends NavigationMenu {
    private final String refCountry;

    public EditCountryPage(final WebDriver driver, final String refCountry) {
        super(driver, false);
        this.refCountry = refCountry;
    }

    public static class Zone {
        public final int id;
        public final String code;
        public final String name;

        public Zone(int id, String code, String name) {
            this.id = id;
            this.code = code;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Zone{" +
                    "id=" + id +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public void open() {
        driver.get(refCountry);
    }

    public final List<Zone> zones = new ArrayList<>();

    private List<WebElement> getZoneRows() {
        return driver.findElements(By.cssSelector("#table-zones > tbody > tr:not([class=header])"));
    }

    private void addZone(WebElement zone) {
        try {
            int id = Integer.valueOf(zone.findElement(By.cssSelector("td:nth-of-type(1) > input")).getAttribute("value"));
            String code = zone.findElement(By.cssSelector("td:nth-of-type(2) > input")).getAttribute("value");
            String name = zone.findElement(By.cssSelector("td:nth-of-type(3) > input")).getAttribute("value");

            zones.add(new Zone(id, code, name));
        } catch (NoSuchElementException nsee) {
        }
    }

    public void read() {
        zones.clear();
        for (WebElement element: getZoneRows()) {
            addZone(element);
        }
    }

    public void print() {
        zones.stream().forEach(System.out::println);
    }

    private String[] alphabeticalOrderZones() {
        String[] alphabeticalOrderZones= new String[zones.size()];
        int index = 0;
        for (EditCountryPage.Zone zone: zones) {
            alphabeticalOrderZones[index++] = zone.name;
        }
        Arrays.sort(alphabeticalOrderZones);

        return alphabeticalOrderZones;
    }

    public boolean isAlphabeticalOrder() {
        if (zones.size() == 0) {
            return false;
        }

        String[] alphabeticalOrderZones = alphabeticalOrderZones();
        for (int index = 0; index < alphabeticalOrderZones.length; index++) {
            if (!alphabeticalOrderZones[index].equals(zones.get(index).name)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "EditCountryPage{" +
                "refCountry='" + refCountry + '\'' +
                '}';
    }
}
