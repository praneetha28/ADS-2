import java.util.ArrayList;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TrieST<Integer> trst;
	ArrayList<String> words;
	private int rows;
	private int cols;
	public BoggleSolver(String[] dictionary) {
		trst = new TrieST<Integer>();
		int[] scores = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		words = new ArrayList<String>();
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].length() > 8) {
				trst.put(dictionary[i], 11);
			} else {
				trst.put(dictionary[i], scores[dictionary[i].length()]);
			}
		}
	}
	public String convert(char ch) {
		String str = " ";
		if (ch == 'Q') {
			str += ch + 'U';
			return str;
		}
		return ch + "";
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public ArrayList<String> getAllValidWords(BoggleBoard board) {
		rows = board.rows();
		cols = board.cols();
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				searchWord(board, i, j, convert(board.getLetter(i, j)), visited);
			}

		}
		return words;
	}
	public boolean isValid(String word) {
		if (!trst.hasPrefix(word)) {
			return false;
		}
		return true;
	}
	public boolean index(int i , int j) {
		if (i < 0 || i >= rows || j < 0 || j >= cols) {
			return false;
		}
		return true;
	}
	private void searchWord(BoggleBoard board, int i, int j, String str, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= rows || j >= cols) {
			return;
		}
		visited[i][j] = true;
		if (trst.contains(str) && !words.contains(str)) {
			words.add(str);
		}
		if (index(i + 1, j + 1) && !visited[i + 1][j + 1] && isValid(str)) {
			searchWord(board, i + 1, j + 1, str + convert(board.getLetter(i + 1, j + 1)), visited);
			visited[i + 1][j + 1] = false;
		}
		if (index(i - 1, j - 1) && !visited[i - 1][j - 1] && isValid(str)) {
			searchWord(board, i - 1, j - 1, str + convert(board.getLetter(i - 1, j - 1)), visited);
			visited[i - 1][j - 1] = false;
		}
		if ( index(i - 1, j + 1) && !visited[i - 1][j + 1] && isValid(str)) {
			searchWord(board, i - 1, j + 1, str + convert(board.getLetter(i - 1, j + 1)), visited);
			visited[i - 1][j + 1] = false;
		}
		if ( index(i + 1, j - 1) && !visited[i + 1][j - 1] && isValid(str)) {
			searchWord(board, i + 1, j - 1, str + convert(board.getLetter(i + 1, j - 1)), visited);
			visited[i + 1][j - 1] = false;

		}
		if ( index(i - 1, j) && !visited[i - 1][j] && isValid(str)) {
			searchWord(board, i - 1, j, str + convert(board.getLetter(i - 1, j)), visited);
			visited[i - 1][j] = false;

		}
		if ( index(i + 1, j) && !visited[i + 1][j] && isValid(str)) {
			searchWord(board, i + 1, j, str + convert(board.getLetter(i + 1, j)), visited);
			visited[i + 1][j] = false;

		}
		if ( index(i, j + 1) && !visited[i][j + 1] && isValid(str)) {
			searchWord(board, i, j + 1, str + convert(board.getLetter(i, j + 1)), visited);
			visited[i][j + 1] = false;

		}
		if (index(i, j - 1) && !visited[i][j - 1] && isValid(str)) {
			searchWord(board, i, j - 1, str + convert(board.getLetter(i, j - 1)), visited);
			visited[i][j - 1] = false;

		}
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		return trst.get(word);
	}
}