package ru.sergeytoropov;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
@RunWith(DataProviderRunner.class)
public class EditCountryPageTest extends StaticInit {
    private static CountriesPage countriesPage;

    static {
        countriesPage = new CountriesPage(driver);
    }

    @Test
    @UseDataProvider("dataProviderEditCountries")
    public void alphabeticalOrder(EditCountryPage editCountryPage) {
        editCountryPage.open();
        editCountryPage.read();

        assertTrue("Зоны расположены не в алфавитном порядке.", editCountryPage.isAlphabeticalOrder());
    }

    @DataProvider
    public static List<List<Object>> dataProviderEditCountries() {
        List<List<Object>> data = new ArrayList<>();

        countriesPage.open();
        countriesPage.read();
        for (EditCountryPage page : countriesPage.getCountriesByZones()) {
            List<Object> elements = new ArrayList<>();
            elements.add(page);
            data.add(elements);
        }
        return data;
    }
}