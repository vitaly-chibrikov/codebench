package stackoverflow;

/**
 * Created by v.chibrikov on 10.12.2014.
 */
public class MyHashMap<K, V> {
    private Bucket<V>[] buckets;

    public MyHashMap() {
        buckets = new Bucket[1024];
    }

    public void put(K key, V Value) {
        int hash = key.hashCode();
        int index = hash % buckets.length;

    }

    private class Bucket<V> {
        private V[] values;
    }

    private class SafeArray<T> {
        private Object[] safeArray;
        private int lastIndex;
        private int initialLength;

        public SafeArray(int initialLength) {
            safeArray = new Object[initialLength];
            lastIndex = 0;
            this.initialLength = initialLength;
        }

        public T get(int index) {
            if (index >= safeArray.length)
                throw new IndexOutOfBoundsException();
            return (T) safeArray[index];
        }

        public void add(T element) {
            safeArray[lastIndex] = element;
            lastIndex++;
        }
    }
}
