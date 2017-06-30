package ru.sergeytoropov.utils;

/**
 * @author sergeytoropov
 * @since 30.06.17
 */
public final class Utils {

    public static boolean isLogicRegularPrice(String color) {
        String[] digits = color.split("[^0-9]+");
        if (digits.length == 5 && digits[1].equals(digits[2]) && digits[2].equals(digits[3])) {
            return true;
        }
        return false;
    }

    public static boolean isLogicCompaignPrice(String color) {
        String[] digits = color.split("[^0-9]+");
        if (digits.length == 5 && digits[2].equals("0") && digits[3].equals("0")) {
            return true;
        }
        return false;
    }
}
