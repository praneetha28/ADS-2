/**.
 * { item_description }
 */
import java.util.HashSet;
/**.
 * Class for boggle solver.
 */
public class BoggleSolver {
	/**.
	 * { var_description }
	 */
	private TrieST trst;
	/**.
	 * Constructs the object.
	 *
	 * @param      dictionary  The dictionary
	 */
	public BoggleSolver(final String[] dictionary) {
		trst = new TrieST();
		for (int i = 0; i < dictionary.length; i++) {
			trst.add(dictionary[i]);
		}
	}
	/**.
	 * Gets all valid words.
	 *
	 * @param      board  The board
	 *
	 * @return     All valid words.
	 */
	public HashSet<String> getAllValidWords(final BoggleBoard board) {
		HashSet<String> words = new HashSet<String>();

		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				searchWord(board, i, j, "", visited, words);
			}

		}
		return words;
	}
	/**.
	 * { function_description }
	 *
	 * @param      board    The board
	 * @param      i        { parameter_description }
	 * @param      j        { parameter_description }
	 * @param      str      The string
	 * @param      visited  The visited
	 * @param      words    The words
	 */
	private void searchWord(final BoggleBoard board, final int i,
	                        final int j, final String str, final boolean[][] visited,
	                        final HashSet words) {
		if (visited[i][j]) {
			return;
		}
		char ch = board.getLetter(i, j);
		String word = str;
		if (ch == 'Q') {
			word += "QU";
		} else {
			word += ch;
		}
		if (!trst.hasPrefix(word)) {
			return;
		}
		if (word.length() > 2 && trst.contains(word)) {
			words.add(word);
		}
		visited[i][j] = true;
		for (int k = -1; k <= 1; k++) {
			for (int l = -1; l <= 1; l++) {
				if (k == 0 && l == 0) {
					continue;
				}
				if ((i + k >= 0) && (i + k < board.rows())
				        && (j + l >= 0) && (j + l < board.cols())) {
					searchWord(board, i + k, j + l, word,
					           visited, words);
				}
			}
		}
		visited[i][j] = false;
	}
	/**.
	 * { function_description }
	 *
	 * @param      word  The word
	 *
	 * @return     { description_of_the_return_value }
	 */
	public int scoreOf(final String word) {
		if (trst.contains(word)) {
			switch (word.length()) {
			case 0:
			case 1:
			case 2:
				return 0;
			case 3:
			case 4:
				return 1;
			case 5:
				return 2;
			case 6:
				return 3;
			case 7:
				return 5;
			default:
				return 11;
			}
		} else {
			return 0;
		}
	}
}