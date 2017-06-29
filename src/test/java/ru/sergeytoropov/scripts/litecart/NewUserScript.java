package ru.sergeytoropov.scripts.litecart;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.sergeytoropov.Init;
import ru.sergeytoropov.db.LiteCartDatabase;
import ru.sergeytoropov.litecart.CreateAccountPage;
import ru.sergeytoropov.litecart.MainPage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
@RunWith(DataProviderRunner.class)
public class NewUserScript extends Init {
    private MainPage mainPage;

    @Before
    public void init() {
        mainPage = new MainPage(driver);
    }

    public static class User {
        public final String taxId;
        public final String company;
        public final String firstName;
        public final String lastName;
        public final String address1;
        public final String address2;
        public final String postCode;
        public final String city;
        public final String email;
        public final String zoneCode;
        public final String phone;
        public final String password;
        public final String confirmPassword;

        public User(String taxId, String company, String firstName, String lastName, String address1, String address2, String postCode, String city, String email, String zoneCode, String phone, String password, String confirmPassword) {
            this.taxId = taxId;
            this.company = company;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address1 = address1;
            this.address2 = address2;
            this.postCode = postCode;
            this.city = city;
            this.email = email;
            this.zoneCode = zoneCode;
            this.phone = phone;
            this.password = password;
            this.confirmPassword = confirmPassword;
        }
    }

    @Test
    @UseDataProvider("dataProviderUsers")
    public void DO(User user) {
        CreateAccountPage accountPage = mainPage.openCreateAccountPage();

        accountPage.setTaxId(user.taxId);
        accountPage.setCompany(user.company);
        accountPage.setFirstName(user.firstName);
        accountPage.setLastName(user.lastName);
        accountPage.setAddress1(user.address1);
        accountPage.setAddress2(user.address2);
        accountPage.setPostCode(user.postCode);
        accountPage.setCity(user.city);
        accountPage.setEmail(user.email);
        accountPage.setZoneCode(user.zoneCode);
        accountPage.setPhone(user.phone);
        accountPage.setPassword(user.password);
        accountPage.setConfirmPassword(user.confirmPassword);

        MainPage mPage = accountPage.createAccount();
        mPage.logout();
        mPage.login(user.email, user.password);
        mPage.logout();
    }

    @DataProvider
    public static List<List<Object>> dataProviderUsers() {
        List<List<Object>> data = new ArrayList<>();
        int nextId = 1;

        try {
            nextId = LiteCartDatabase.getNextCustomersId();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for (int id = nextId; id < nextId + 3; id++) {
            User user = new User(
                    "taxId_" + id,
                    "company_" + id,
                    "Ivan_" + id,
                    "Petrov_" + id,
                    "address1_" + id,
                    "address2_" + id ,
                    "" + (10000 + id),
                    "city_" + id,
                    "email_" + id + "@email.com",
                    "Arizona",
                    "" + (9502810000L + id),
                    "password",
                    "password");

            List<Object> userList = new ArrayList<>();
            userList.add(user);
            data.add(userList);
        }
        return data;
    }
}
