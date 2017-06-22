package ru.sergeytoropov.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sergeytoropov
 * @since 07.06.17
 */
public class CountriesPage extends NavigationMenu {

    public CountriesPage(final WebDriver driver) {
        super(driver);
    }

    public void open() {
        // Позиция в меню (Возможно это не лучший вариант. Может следует искать позиция в меню по названию?)
        chooseMenuItem(new MenuItem(3, 0));
    }

    public static class Country {
        public final int id;
        public final String code;
        public final String name;
        public final String refCountry;
        public final int quantityZones;

        public Country(int id, String code, String name, String refCountry, int quantityZones) {
            this.id = id;
            this.code = code;
            this.name = name;
            this.refCountry = refCountry;
            this.quantityZones = quantityZones;
        }

        @Override
        public String toString() {
            return "Country{" +
                    "id=" + id +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", refCountry='" + refCountry + '\'' +
                    ", quantityZones=" + quantityZones +
                    '}';
        }
    }

    public final List<Country> countries = new ArrayList<>();

    /**
     * Возвращает список стран на редактирование у которых количесво зон более нуля.
     *
     * @return
     */
    public List<EditCountryPage> getCountriesByZones() {
        return getCountriesByZones(0);
    }

    /**
     * Возвращает список стран на редактирование у которых количество зон больше заданного параметра.
     *
     * @param quantityZones
     * @return
     */
    public List<EditCountryPage> getCountriesByZones(int quantityZones) {
        List<EditCountryPage> editCountries = new ArrayList<>();

        for (Country country: countries) {
            if (country.quantityZones > quantityZones) {
               editCountries.add(new EditCountryPage(getDriver(), country.refCountry));
            }
        }
        return editCountries;
    }

    private List<WebElement> getCountryRows() {
        return driver.findElements(By.cssSelector("#content > form > table > tbody > tr[class=row]"));
    }

    private void addCountry(WebElement country) {
        int id  = Integer.valueOf(country.findElement(By.cssSelector("td:nth-of-type(3)")).getText());
        String code = country.findElement(By.cssSelector("td:nth-of-type(4)")).getText();
        String name = country.findElement(By.cssSelector("td:nth-of-type(5) > a")).getText();
        String refName = country.findElement(By.cssSelector("td:nth-of-type(5) > a")).getAttribute("href");
        int quantityZones = Integer.valueOf(country.findElement(By.cssSelector("td:nth-of-type(6)")).getText());

        countries.add(new Country(id, code, name, refName, quantityZones));
    }

    public void read() {
        countries.clear();
        for (WebElement element: getCountryRows()) {
            addCountry(element);
        }
    }

    private String[] alphabeticalOrderCountries() {
        String[] alphabeticalOrderCountries = new String[countries.size()];
        int index = 0;
        for (Country country: countries) {
            alphabeticalOrderCountries[index++] = country.name;
        }
        Arrays.sort(alphabeticalOrderCountries);

        return alphabeticalOrderCountries;
    }

    public boolean isAlphabeticalOrder() {
        if (countries.size() == 0) {
            return false;
        }

        String[] alphabeticalOrderCountries = alphabeticalOrderCountries();
        for (int index = 0; index < alphabeticalOrderCountries.length; index++) {
            if (!alphabeticalOrderCountries[index].equals(countries.get(index).name)) {
                return false;
            }
        }
        return true;
    }
}
