import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private Picture pic;
	private int height;
	private int width;
	public SeamCarver(Picture picture) {
		pic = picture;
		height = pic.height();
		width = pic.width();
	}
	// current picture
	public Picture picture() {
		return pic;
	}
	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		if (x == 0 || x == pic.width() - 1 || y == 0 || y == pic.height() - 1) {
			return 1000;
		}
		double energy = calculateEnergy(pic.get(x - 1, y), pic.get(x + 1, y)) + calculateEnergy(pic.get(x, y - 1), pic.get(x, y + 1));
		return Math.sqrt(energy);
	}
	public double calculateEnergy(Color c1, Color c2) {
		double red = c1.getRed() - c2.getRed();
		double blue = c1.getBlue() - c2.getBlue();
		double green = c1.getGreen() - c2.getGreen();
		return red * red + blue * blue + green * green;
	}

	// // sequence of indices for horizontal seam
	// public int[] findHorizontalSeam() {
	// 	return new int[0];
	// }

	// // sequence of indices for vertical seam
	// public int[] findVerticalSeam() {
	// 	return new int[0];
	// }

	// // remove horizontal seam from current picture
	// public void removeHorizontalSeam(int[] seam) {

	// }

	// // remove vertical seam from current picture
	// public void removeVerticalSeam(int[] seam) {

	// }
}