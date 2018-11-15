import java.util.*;
public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TrieST trst;
	public BoggleSolver(String[] dictionary) {
		trst = new TrieST();
		for (int i = 0; i < dictionary.length; i++) {
				trst.add(dictionary[i]);
		}
	}
// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public HashSet<String> getAllValidWords(BoggleBoard board) {
		HashSet<String> words = new HashSet<String>();

		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				boolean[][] visited = new boolean[board.rows()][board.cols()];
				searchWord(board, i, j, "", visited, words);
			}

		}
		return words;
	}
	private void searchWord(BoggleBoard board, int i, int j, String str, boolean[][] visited, HashSet words) {
		if (visited[i][j]) {
			return;
		}
		char ch = board.getLetter(i, j);
		String word = str;
		if (ch =='Q') {
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
                if ((i + k >= 0) && (i + k < board.rows()) && (j + l >= 0) && (j + l < board.cols())) {
                    searchWord(board, i + k, j + l, word, visited, words);
                }
            }
        }
        visited[i][j] = false;
	}
	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A through Z.)
	public int scoreOf(String word) {
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