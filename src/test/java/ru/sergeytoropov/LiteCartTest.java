package ru.sergeytoropov;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author sergeytoropov
 * @since 02.06.17
 */
public class LiteCartTest extends Init {
    private LiteCart liteCart;

    @Before
    public void init() {
        liteCart = new LiteCart(driver);
    }

    @Test
    public void login() {
        liteCart.login();
        sleep();
    }
}