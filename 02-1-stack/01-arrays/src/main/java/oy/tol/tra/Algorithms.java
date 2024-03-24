package oy.tol.tra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Algorithms {

    public static <T extends Comparable<T>> void sort(T[] array) {
        List<T> list = Arrays.asList(array);
        Collections.sort(list);
        list.toArray(array);
    }

    public static <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }
}
