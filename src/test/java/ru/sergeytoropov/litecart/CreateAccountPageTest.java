package ru.sergeytoropov.litecart;

import org.junit.Test;
import ru.sergeytoropov.StaticInit;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 20.06.17
 */
public class CreateAccountPageTest extends StaticInit{
    private static CreateAccountPage createAccountPage;

    static {
        createAccountPage = (new MainPage(driver)).openCreateAccountPage();
    }

    @Test
    public void taxId() {
        String value = "taxId";
        createAccountPage.setTaxId(value);
        assertTrue(value.equals(createAccountPage.getTaxId()));
    }

    @Test
    public void company() {
        String value = "company";
        createAccountPage.setCompany(value);
        assertTrue(value.equals(createAccountPage.getCompany()));
    }

    @Test
    public void firstName() {
        String value = "firstName";
        createAccountPage.setFirstName(value);
        assertTrue(value.equals(createAccountPage.getFirstName()));
    }

    @Test
    public void lastName() {
        String value = "lastName";
        createAccountPage.setLastName(value);
        assertTrue(value.equals(createAccountPage.getLastName()));
    }

    @Test
    public void address1() {
        String value = "address1";
        createAccountPage.setAddress1(value);
        assertTrue(value.equals(createAccountPage.getAddress1()));
    }

    @Test
    public void address2() {
        String value = "address2";
        createAccountPage.setAddress2(value);
        assertTrue(value.equals(createAccountPage.getAddress2()));
    }

    @Test
    public void postcode() {
        String value = "postcode";
        createAccountPage.setPostCode(value);
        assertTrue(value.equals(createAccountPage.getPostCode()));
    }

    @Test
    public void city() {
        String value = "city";
        createAccountPage.setCity(value);
        assertTrue(value.equals(createAccountPage.getCity()));
    }

    @Test
    public void email() {
        String value = "email";
        createAccountPage.setEmail(value);
        assertTrue(value.equals(createAccountPage.getEmail()));
    }

    @Test
    public void phone() {
        String value = "phone";
        createAccountPage.setPhone(value);
        assertTrue(value.equals(createAccountPage.getPhone()));
    }

    @Test
    public void password() {
        String value = "password";
        createAccountPage.setPassword(value);
        assertTrue(value.equals(createAccountPage.getPassword()));
    }

    @Test
    public void confirmPassword() {
        String value = "confirmPassword";
        createAccountPage.setConfirmPassword(value);
        assertTrue(value.equals(createAccountPage.getConfirmPassword()));
    }

    @Test
    public void country() {
        createAccountPage.setCountry();
    }

    @Test
    public void zoneCode() {
        String value = "Arizona";
        createAccountPage.setZoneCode("Arizona");
        assertTrue(value.equals(createAccountPage.getZoneCode()));
    }

    @Test
    public void createAccount() {
        createAccountPage.buttonCreateAccount();
    }
}