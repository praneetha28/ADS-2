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
     *
     * @param      args  The arguments
     */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
			case "loadDictionary":
				// input000.txt and output000.txt
				BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
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
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}
	/**.
	 * Loads a dictionary.
	 *
	 * @param      file  The file
	 *
	 * @return     { description_of_the_return_value }
	 */
	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] dictionary = toReadFile(file);
		for (int i = 0; i < dictionary.length; i++) {
			for (String word : dictionary[i].split(" ")) {
				if (st.contains(word.toLowerCase())) {
					st.put(word.toLowerCase(), st.get(word.toLowerCase()) + 1);
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
	 *
	 * @param      prefix  The prefix
	 *
	 * @return     All words.
	 */
	public Iterable<String> getAllWords(final String prefix) {
		return tst.keysWithPrefix(prefix);

	}
	/**.
	 * { function_description }
	 *
	 * @param      t9Signature  The t 9 signature
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> potentialWords(final String t9Signature) {
		return tst.keysThatMatch(t9Signature);
	}
	/**.
	 * Gets the suggestions.
	 *
	 * @param      words  The words
	 * @param      k      { parameter_description }
	 *
	 * @return     The suggestions.
	 */
	public Iterable<String> getSuggestions(final Iterable<String> words, final int k) {
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
	 *
	 * @param      t9Signature  The t 9 signature
	 * @param      k            { parameter_description }
	 *
	 * @return     { description_of_the_return_value }
	 */
	public Iterable<String> t9(final String t9Signature, final int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}