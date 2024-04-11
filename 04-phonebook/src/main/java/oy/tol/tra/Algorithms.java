package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

       
        T[] leftArray = (T[]) new Comparable[n1];
        T[] rightArray = (T[]) new Comparable[n2];

        
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

     
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

       
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static <T> void reverse(T[] array) {
        if (array == null || array.length <= 1) {
            return; 
        }

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

    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (array == null || fromIndex < 0 || toIndex >= array.length || fromIndex > toIndex) {
            throw new IllegalArgumentException("参数无效");
        }

        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            int comparisonResult = value.compareTo(array[mid]);

            if (comparisonResult == 0) {
                return mid; 
            } else if (comparisonResult < 0) {
                toIndex = mid - 1; 
            } else {
                fromIndex = mid + 1; 
            }
        }

        return -1; 
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }

    public static <K extends Comparable<K>, V> int partitionByRule(Pair<K, V>[] pairs, int count, Predicate judgeNullPredicate) {
        if (pairs == null || count <= 0) {
            return 0;
        }

        int left = 0;
        int right = count - 1;

        while (left <= right) {
            if (pairs[left] == null) {
                swap(pairs, left, right);
                right--;
            } else {
                left++;
            }
        }

        return left;
    }

    private static <K extends Comparable<K>, V> void swap(Pair<K, V>[] pairs, int i, int j) {
        Pair<K, V> temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }
}
