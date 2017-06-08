package ru.sergeytoropov.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sergeytoropov
 * @since 08.06.17
 */
public final class AlphabeticalOrder {

    public static boolean list(String[] list) {
        String[] sortedList = Arrays.copyOf(list, list.length);
        Arrays.sort(sortedList);

        for (int index = 0; index < sortedList.length; index++) {
            if (!sortedList[index].equals(list[index])) {
                return false;
            }
        }
        return true;
    }

    public static boolean list(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);

        for (int index = 0; index < sortedList.size(); index++) {
            if (!sortedList.get(index).equals(list.get(index))) {
                return false;
            }
        }
        return true;
    }
}
