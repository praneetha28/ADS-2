/**.
 * { item_description }
 */
import java.util.Comparator;
/**.
 * { item_description }
 */
import java.util.Iterator;
/**.
 * { item_description }
 */
import java.util.NoSuchElementException;
/**.
 * Class for minimum pq.
 *
 * @param      <Key>  The key
 */
public class MinPQ<Key> implements Iterable<Key> {
    /**.
     * { var_description }
     */
    private Key[] pq;
    /**.
     * { var_description }
     */
    private int n;
    /**.
     * { var_description }
     */
    private Comparator<Key> comparator;
    /**.
     * Constructs the object.
     *
     * @param      intCapacity  The initialize capacity
     */
    public MinPQ(final int intCapacity) {
        pq = (Key[]) new Object[intCapacity + 1];
        n = 0;
    }
    /**.
     * Constructs the object.
     */
    public MinPQ() {
        this(1);
    }
    /**.
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param  intCapacity the initial capacity of this priority queue
     * @param  compartor the order in which to compare the keys
     */
    public MinPQ(final int intCapacity, final Comparator<Key> compartor) {
        this.comparator = compartor;
        pq = (Key[]) new Object[intCapacity + 1];
        n = 0;
    }

    // /**.
    //  * Initializes an empty priority queue using the given comparator.
    //  *
    //  * @param  compartor the order in which to compare the keys
    //  */
    // public MinPQ(final Comparator<Key> compartor) {
    //     this(1, comparator);
    // }

    /**.
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using
     *  sink-based heap construction.
     *
     * @param  key the array of keys
     */
    // public MinPQ(final Key[] key) {
    //     n = key.length;
    //     pq = (Key[]) new Object[keys.length + 1];
    //     for (int i = 0; i < n; i++) {
    //         pq[i + 1] = keys[i];
    //     }
    //     for (int k = n / 2; k >= 1; k--) {
    //         sink(k);
    //     }
    //     assert isMinHeap();
    // }
    /**.
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }
    /**.
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }
    /**.
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException(
            "Priority queue underflow");
        }
        return pq[1];
    }
    /**.
     * { function_description }
     *
     * @param      capacity  The capacity
     */
    private void resize(final int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }
// time complexity in average case is log N
    /**.
     * Adds a new key to this priority queue.
     *
     * @param  x the key to add to this priority queue
     */
    public void insert(final Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }
//time complexity in average case is log N
    /**.
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     */
    public Key delMin() {
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 2 + 2)) {
            resize(pq.length / 2);
        }
        assert isMinHeap();
        return min;
    }
    /**.
     * time complexity in average case is log N
     * @param  l the key to shift
     */
    private void swim(final int l) {
        int k = l;
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }
    /**.
     * time complexity in average case is log N
     * @param  l the key to shift
     */
    private void sink(final int l) {
        int k = l;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
    /**.
     * { function_description }
     * time complexity is 1
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean greater(final int i, final int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }
    /**.
     * { function_description }
     * time complexity is 1
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     */
    private void exch(final int i, final int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }
    /**.
     * Determines if minimum heap.
     * time complexity is 1
     * @return     True if minimum heap, False otherwise.
     */
    private boolean isMinHeap() {
        return isMinHeap(1);
    }
    /**.
     * Determines if minimum heap.
     *
     * @param      k     { parameter_description }
     *
     * @return     True if minimum heap, False otherwise.
     */
    private boolean isMinHeap(final int k) {
        if (k > n) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left  <= n && greater(k, left)) {
            return false;
        }
        if (right <= n && greater(k, right)) {
            return false;
        }
        return isMinHeap(left) && isMinHeap(right);
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }
    /**.
     * Class for heap iterator.
     */
    private class HeapIterator implements Iterator<Key> {
        /**.
         * { var_description }
         */
        private MinPQ<Key> copy;
        /**.
         * Constructs the object.
         */
        public HeapIterator() {
            if (comparator == null) {
                copy = new MinPQ<Key>(size());
            } else {
                copy = new MinPQ<Key>(size(), comparator);
            }
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }
        /**.
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return !copy.isEmpty();
        }
        /**.
         * { function_description }
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**.
         * { function_description }
         *
         * @return     { description_of_the_return_value }
         */
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }

    // /**
    //  * Unit tests the {@code MinPQ} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     MinPQ<String> pq = new MinPQ<String>();
    //     while (!StdIn.isEmpty()) {
    //         String item = StdIn.readString();
    //         if (!item.equals("-")) pq.insert(item);
    //         else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
    //     }
    //     StdOut.println("(" + pq.size() + " left on pq)");
    // }

}
