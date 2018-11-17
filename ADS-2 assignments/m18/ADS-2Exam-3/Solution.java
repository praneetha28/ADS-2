/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * { item_description }
 */
import java.util.TreeSet;
/**
 * Class for solution.
 */
public final class Solution {

    /**
     * Constructs the object.
     */
    private Solution() {
        // empty constructor
    }

    /**
     * Main method.
     * Time compexity is O(L + log N) where
     * L- length of string
     * N - number of strings
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();

        switch (cases) {
            case "loadDictionary":
                // input000.txt and output000.txt
                BinarySearchST<String, Integer> hash = loadDictionary(
                    "/Files/t9.csv");
                while (scan.hasNextLine()) {
                    String key = scan.nextLine();
                    System.out.println(hash.get(key));
                }
                break;

            case "getAllPrefixes":
                // input001.txt and output001.txt
                T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
                while (scan.hasNextLine()) {
                    String prefix = scan.nextLine();
                    for (String each : t9.getAllWords(prefix)) {
                        System.out.println(each);
                    }
                }
                break;

            case "potentialWords":
                // input002.txt and output002.txt
                t9 = new T9(loadDictionary("/Files/t9.csv"));
                int count = 0;
                while (scan.hasNextLine()) {
                    String t9Signature = scan.nextLine();
                    for (String each : t9.potentialWords(t9Signature)) {
                        count++;
                        System.out.println(each);
                    }
                }
                if (count == 0) {
                    System.out.println("No valid words found.");
                }
                break;

            case "topK":
                // input003.txt and output003.txt
                t9 = new T9(loadDictionary("/Files/t9.csv"));
                Bag<String> bag = new Bag<String>();
                int k = Integer.parseInt(scan.nextLine());
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    bag.add(line);
                }
                for (String each : t9.getSuggestions(bag, k)) {
                    System.out.println(each);
                }

                break;

            case "t9Signature":
                // input004.txt and output004.txt
                t9 = new T9(loadDictionary("/Files/t9.csv"));
                bag = new Bag<String>();
                k = Integer.parseInt(scan.nextLine());
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    for (String each : t9.t9(line, k)) {
                        System.out.println(each);
                    }
                }
                break;

            default:
                break;

        }
    }
    /**.
     * { function_description }
     * Time complexity is O(1)
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**.
     * Loads a dictionary.
     * Time complexity is O(N^2)
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String, Integer> loadDictionary(
        final String file) {
        BinarySearchST<String, Integer>  st = new
        BinarySearchST<String, Integer>();
        // your code goes here
        String[] dictionary = toReadFile(file);
        for (int i = 0; i < dictionary.length; i++) {
            for (String word : dictionary[i].split(" ")) {
                if (st.contains(word.toLowerCase())) {
                    st.put(word.toLowerCase(), st.get(
                        word.toLowerCase()) + 1);
                } else {
                    st.put(word.toLowerCase(), 1);
                }
            }
        }
        return st;
    }

}
/**.
 * Class for t 9.
 */
class T9 {
    /**.
     * { var_description }
     */
    private BinarySearchST<String, Integer> bst;
    /**.
     * { var_description }
     */
    private TST<Integer> tst;
    /**.
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    public T9(final BinarySearchST<String, Integer> st) {
        // your code goes here
        bst = st;
        tst = new TST<Integer>();
        for (String key : bst.keys()) {
            tst.put(key, bst.get(key));
        }
    }
    /**.
     * Gets all words.
     * Time complexity is O(1)
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        return tst.keysWithPrefix(prefix);

    }
    /**.
     * { function_description }
     * Time complexity is O(N^2)
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        TreeSet<String> list = new TreeSet<String>();
        for (String word : tst.keys()) {
            String pattern = "";
            for (char ch : word.toCharArray()) {
                if (ch == 'a' || ch == 'b' || ch == 'c') {
                    pattern += "2";
                } else if (ch == 'd' || ch == 'e' || ch == 'f') {
                    pattern += "3";
                } else if (ch == 'g' || ch == 'h' || ch == 'i') {
                    pattern += "4";
                } else if (ch == 'j' || ch == 'k' || ch == 'l') {
                    pattern += "5";
                } else if (ch == 'm' || ch == 'n' || ch == 'o') {
                    pattern += "6";
                } else if (ch == 'p' || ch == 'q' || ch == 'r' || ch == 's') {
                    pattern += "7";
                } else if (ch == 't' || ch == 'u' || ch == 'v') {
                    pattern += "8";
                } else {
                    pattern += "9";
                }
            }
            if (pattern.equals(t9Signature)) {
                list.add(word);
            }
        }
        return list;
    }
    /**.
     * Gets the suggestions.
     * Time complexity is O(N^2)
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
     final int k) {
        TreeSet<String> wordList = new TreeSet<String>();
        MaxPQ<Integer> valuemax = new MaxPQ<Integer>();
        for (String word : words) {
            valuemax.insert(tst.get(word));
        }
        for (int i = 0; i < k; i++) {
            int value = valuemax.delMax();
            for (String word : words) {
                if (value == tst.get(word)) {
                    wordList.add(word);
                }
            }
        }
        return wordList;
    }
    /**.
     * { function_description }
     * Time complexity is O(1).
     * @param      t9Signature  The t 9 signature
     * @param      k            { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
