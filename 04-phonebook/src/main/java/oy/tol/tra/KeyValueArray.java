package oy.tol.tra;

public class KeyValueArray<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Pair<K, V>[] pairs = null;
    private int count = 0;
    private int reallocationCount = 0;

    public KeyValueArray(int capacity) {
        ensureCapacity(capacity);
    }

    public KeyValueArray() {
        ensureCapacity(20);
    }

    @Override
    public Type getType() {
        return Type.SLOW;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        if (size < 20) {
            size = 20;
        }
        if (pairs == null || size > pairs.length) {
            Pair<K, V>[] newPairs = (Pair<K, V>[]) new Pair[size];
            if (pairs != null) {
                System.arraycopy(pairs, 0, newPairs, 0, count);
            }
            pairs = newPairs;
            reallocationCount++;
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        double fillRate = (count / (double) pairs.length) * 100.0;
        return String.format("KeyValueArray reallocated %d times, each time doubles the size\n" +
                "KeyValueArray fill rate is %.2f%%\n", reallocationCount, fillRate);
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        for (int i = 0; i < count; i++) {
            if (pairs[i] != null && pairs[i].getKey().equals(key)) {
                pairs[i].setValue(value);
                return true;
            }
        }
        if (count >= pairs.length) {
            ensureCapacity(pairs.length * 2);
        }
        if (count < pairs.length) {
            pairs[count++] = new Pair<>(key, value);
            return true;
        }
        return false;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        for (int i = 0; i < count; i++) {
            if (pairs[i] != null && key.equals(pairs[i].getKey())) {
                return pairs[i].getValue();
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<K, V>[] toSortedArray() {
        Pair<K, V>[] sorted = (Pair<K, V>[]) new Pair[count];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (pairs[i] != null) {
                sorted[index++] = new Pair<>(pairs[i].getKey(), pairs[i].getValue());
            }
        }
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        int indexOfFirstNull = Algorithms.partitionByRule(pairs, count, element -> element == null);
        ensureCapacity(indexOfFirstNull);
    }
}
