/**.
 * { item_description }
 */
import java.util.Iterator;
/**.
 * { item_description }
 */
import java.util.NoSuchElementException;
/**.
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * { var_description }
     */
    private int N_1;
    /**.
     * { var_description }
     */
    private Node first;
    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * { var_description }
         */
        private Item item;
        /**.
         * { var_description }
         */
        private Node next;
    }

   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        N = 0;
    }
/**.
 * Determines if empty.
 *
 * @return     True if empty, False otherwise.
 */
    public boolean isEmpty() {
        return first == null;
    }
/**.
 * { function_description }
 *
 * @return     { description_of_the_return_value }
 */
    public int size() {
        return N_1;
    }
/**.
 * { function_description }
 *
 * @param      item  The item
 */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
/**.
 * { function_description }
 *
 * @return     { description_of_the_return_value }
 */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
/**.
 * Class for list iterator.
 */
    private class ListIterator implements Iterator<Item> {
        /**.
         * { var_description }
         */
        private Node current = first;
        /**.
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
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
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
