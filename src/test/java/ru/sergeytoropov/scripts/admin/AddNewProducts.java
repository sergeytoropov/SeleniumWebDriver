package ru.sergeytoropov.scripts.admin;

import org.junit.Before;
import org.junit.Test;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.admin.CatalogPage;
import ru.sergeytoropov.admin.NewProductPage;
import ru.sergeytoropov.db.LiteCartDatabase;

import java.sql.SQLException;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 30.06.17
 */
public class AddNewProducts extends Init {
    CatalogPage catalogPage;

    @Before
    public void init() {
        catalogPage = new CatalogPage(driver);
        sleep();
    }

    public String getUniqName() {
        try {
            return "product" + LiteCartDatabase.getNextProductId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    @Test
    public void addNewProducts() {
        IntStream.range(0, 9).forEach(index -> {
            NewProductPage newProductPage = catalogPage.openAddNewProduct();

            String uniqName = getUniqName();
            newProductPage.add(uniqName);
            catalogPage = newProductPage.save();

            assertTrue(catalogPage.find(uniqName));
        });
    }
}
