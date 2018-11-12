/**.
 * { item_description }
 */
import java.awt.Color;
/**.
 * { item_description }
 */
import java.awt.FileDialog;
/**.
 * { item_description }
 */
import java.awt.Toolkit;
/**.
 * { item_description }
 */
import java.awt.event.ActionEvent;
/**.
 * { item_description }
 */
import java.awt.event.ActionListener;
/**.
 * { item_description }
 */
import java.awt.event.KeyEvent;
/**.
 * { item_description }
 */
import java.awt.image.BufferedImage;
/**.
 * { item_description }
 */
import java.io.File;
/**.
 * { item_description }
 */
import java.io.IOException;
/**.
 * { item_description }
 */
import java.net.URL;
/**.
 * { item_description }
 */
import javax.imageio.ImageIO;
/**.
 * { item_description }
 */
import javax.swing.ImageIcon;
/**.
 * { item_description }
 */
import javax.swing.JFrame;
/**.
 * { item_description }
 */
import javax.swing.JLabel;
/**.
 * { item_description }
 */
import javax.swing.JMenu;
/**.
 * { item_description }
 */
import javax.swing.JMenuBar;
/**.
 * { item_description }
 */
import javax.swing.JMenuItem;
/**.
 * { item_description }
 */
import javax.swing.JPanel;
/**.
 * { item_description }
 */
import javax.swing.KeyStroke;
/**.
 * { item_description }
 */
public final class Picture implements ActionListener {
	/**.
	 * { var_description }
	 */
	private BufferedImage image;
	/**.
	 * { item_description }
	 */
	private JFrame frame;
	/**.
	 * { item_description }
	 */
	private String filename;
	/**.
	 * { item_description }
	 */
	private boolean isOriginUpperLeft = true;
	/**.
	 * { item_description }
	 */
	private final int width, height;
	/**.
	  * Creates a {@code width}-by-{@code height} picture,
	  * with {@code width} columns
	  * and {@code height} rows, where each pixel is black.
	  *
	  * @param width the width of the picture
	  * @param height the height of the picture
	  * @throws IllegalArgumentException if {@code width} is negative
	  * @throws IllegalArgumentException if {@code height} is negative
	  */
	public Picture(final int width, final int height) {
		if (width  < 0) {
			throw new IllegalArgumentException("width must be non-negative");
		}
		if (height < 0) {
			throw new IllegalArgumentException("height must be non-negative");
		}
		this.width  = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}

	/**.
	  * Creates a new picture that is a deep copy of the argument picture.
	  *
	  * @param  picture the picture to copy
	  * @throws IllegalArgumentException if {@code picture} is {@code null}
	  */
	public Picture(final Picture picture) {
		if (picture == null) {
			throw new
			IllegalArgumentException("constructor argument is null");
		}
		width  = picture.width();
		height = picture.height();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		filename = picture.filename;
		isOriginUpperLeft = picture.isOriginUpperLeft;
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				image.setRGB(col, row, picture.image.getRGB(col, row));
			}
		}
	}

	/**
	  * Creates a picture by reading an image from a file or URL.
	  *
	  * @param  filename the name of the file (.png, .gif, or .jpg) or URL.
	  * @throws IllegalArgumentException if cannot read image
	  * @throws IllegalArgumentException if {@code filename} is {@code null}
	  */
	public Picture(final String filename) {
		if (filename == null) {
			throw new
			IllegalArgumentException("constructor argument is null");
		}

		this.filename = filename;
		try {
			// try to read from file in working directory
			File file = new File(filename);
			if (file.isFile()) {
				image = ImageIO.read(file);
			}
			else {
				URL url = getClass().getResource(filename);
				if (url == null) {
					url = new URL(filename);
				}
				image = ImageIO.read(url);
			}

			if (image == null) {
				throw new
				IllegalArgumentException("could not read image file: " + filename);
			}

			width  = image.getWidth(null);
			height = image.getHeight(null);
		} catch (IOException ioe) {
			throw new
			IllegalArgumentException("could not open image file: " + filename, ioe);
		}
	}

	/**
	  * Creates a picture by reading the image from a PNG, GIF, or JPEG file.
	  *
	  * @param file the file
	  * @throws IllegalArgumentException if cannot read image
	  * @throws IllegalArgumentException if {@code file} is {@code null}
	  */
	public Picture(final File file) {
		if (file == null) {
			throw new
			IllegalArgumentException("constructor argument is null");
		}
		try {
			image = ImageIO.read(file);
		} catch (IOException ioe) {
			throw new
			IllegalArgumentException("could not open file: " + file, ioe);
		}
		if (image == null) {
			throw new
			IllegalArgumentException("could not read file: " + file);
		}
		width  = image.getWidth(null);
		height = image.getHeight(null);
		filename = file.getName();
	}

	/**
	  * Returns a {@link JLabel} containing this picture,
	  *  for embedding in a {@link JPanel},
	  * {@link JFrame} or other GUI widget.
	  *
	  * @return the {@code JLabel}
	  */
	public JLabel getJLabel() {
		if (image == null) return null;
		ImageIcon icon = new ImageIcon(image);
		return new JLabel(icon);
	}
	/**
	  * Sets the origin to be the upper left pixel. This is the default.
	  */
	public void setOriginUpperLeft() {
		isOriginUpperLeft = true;
	}

	/**
	  * Sets the origin to be the lower left pixel.
	  */
	public void setOriginLowerLeft() {
		isOriginUpperLeft = false;
	}

	/**
	  * Displays the picture in a window on the screen.
	  */
	public void show() {
		if (frame == null) {
			frame = new JFrame();
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("File");
			menuBar.add(menu);
			JMenuItem menuItem1 = new JMenuItem(" Save...   ");
			menuItem1.addActionListener(this);
			menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
			                         Toolkit.getDefaultToolkit().
			                         getMenuShortcutKeyMask()));
			menu.add(menuItem1);
			frame.setJMenuBar(menuBar);
			frame.setContentPane(getJLabel());
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if (filename == null) frame.setTitle(width + "-by-" + height);
			else                  frame.setTitle(filename);
			frame.setResizable(false);
			frame.pack();
			frame.setVisible(true);
		}

		// draw
		frame.repaint();
	}

	/**
	  * Returns the height of the picture.
	  *
	  * @return the height of the picture (in pixels)
	  */
	public int height() {
		return height;
	}

	/**
	  * Returns the width of the picture.
	  *
	  * @return the width of the picture (in pixels)
	  */
	public int width() {
		return width;
	}

	private void validateRowIndex(int row) {
		if (row < 0 || row >= height()) {
			throw new IllegalArgumentException(
				"row index must be between 0 and " +
				(height() - 1) + ": " + row);
		}
	}

	private void validateColumnIndex(int col) {
		if (col < 0 || col >= width()) {
			throw new IllegalArgumentException(
				"column index must be between 0 and " +
				(width() - 1) + ": " + col);
		}
	}

	/**
	  * Returns the color of pixel
	  *
	  * @param col the column index
	  * @param row the row index
	  * @return the color of pixel ({@code col}, {@code row})
	  * @throws IllegalArgumentException unless both width and height
	  */
	public Color get(final int col, final int row) {
		validateColumnIndex(col);
		validateRowIndex(row);
		int rgb = getRGB(col, row);
		return new Color(rgb);
	}

	/**
	  * Returns the color of pixel
	  *
	  * @param col the column index
	  * @param row the row index
	  * @return the integer representation of the color of pixel
	  * @throws IllegalArgumentException unless both width and height
	  */
	public int getRGB(final int col, final int row) {
		validateColumnIndex(col);
		validateRowIndex(row);
		if (isOriginUpperLeft) {
			return image.getRGB(col, row);
		} else {
			return image.getRGB(col, height - row - 1);
		}
	}

	/**
	  * Sets the color of pixel ({@code col}, {@code row}) to given color.
	  *
	  * @param col the column index
	  * @param row the row index
	  * @param color the color
	  * @throws IllegalArgumentException unless both width and height
	  * @throws IllegalArgumentException if {@code color} is {@code null}
	  */
	public void set(final int col, final int row, final Color color) {
		validateColumnIndex(col);
		validateRowIndex(row);
		if (color == null) {
			throw new IllegalArgumentException("color argument is null");
		}
		int rgb = color.getRGB();
		setRGB(col, row, rgb);
	}

	/**
	  * Sets the color of pixel ({@code col}, {@code row}) to given color.
	  *
	  * @param col the column index
	  * @param row the row index
	  * @param rgb the integer representation of the color
	  * @throws IllegalArgumentException unless both width and height
	  */
	public void setRGB(final int col, final int row, final int rgb) {
		validateColumnIndex(col);
		validateRowIndex(row);
		if (isOriginUpperLeft) {
			image.setRGB(col, row, rgb);
		} else {
		    image.setRGB(col, height - row - 1, rgb);
		}
	}

	/**
	  * Returns true if this picture is equal to the argument picture.
	  *
	  * @param other the other picture
	  * @return {@code true} if this picture is the same dimension as
	  *         and if all pixels have the same color; {@code false}
	  */
	public boolean equals(final Object other) {
		if (other == this) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other.getClass() != this.getClass()) {
			return false;
		}
		Picture that = (Picture) other;
		if (this.width()  != that.width()) {
			return false;
		}
		if (this.height() != that.height()) {
			return false;
		}
		for (int col = 0; col < width(); col++) {
			for (int row = 0; row < height(); row++) {
				if (this.getRGB(col, row) != that.getRGB(col, row)) return false;
			}
		}
		return true;
	}

	/**
	  * Returns a string representation of this picture.
	  * the red, green, and blue components.
	  *
	  * @return a string representation of this picture
	  */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(width + "-by-" + height +
		 " picture (RGB values given in hex)\n");
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int rgb = 0;
				if (isOriginUpperLeft) {
					rgb = image.getRGB(col, row);
				} else {
					rgb = image.getRGB(col, height - row - 1);
				}
				sb.append(String.format("#%06X ", rgb & 0xFFFFFF));
			}
			sb.append("\n");
		}
		return sb.toString().trim();
	}

	/**
	 * This operation is not supported because pictures are mutable.
	 *
	 * @return does not return a value
	 * @throws UnsupportedOperationException if called
	 */
	public int hashCode() {
		throw new UnsupportedOperationException(
			"hashCode() is not supported because pictures are mutable");
	}

	/**
	  * Saves the picture to a file in either PNG or JPEG format.
	  * The filetype extension must be either .png or .jpg.
	  *
	  * @param name the name of the file
	  * @throws IllegalArgumentException if {@code name} is {@code null}
	  */
	public void save(final String name) {
		if (name == null) {
			throw new IllegalArgumentException("argument to save() is null");
		}
		save(new File(name));
		filename = name;
	}

	/**
	  * Saves the picture to a file in a PNG or JPEG image format.
	  *
	  * @param  file the file
	  * @throws IllegalArgumentException if {@code file} is {@code null}
	  */
	public void save(final File file) {
		if (file == null) {
			throw new IllegalArgumentException("argument to save() is null");
		}
		filename = file.getName();
		if (frame != null) {
			frame.setTitle(filename);
		}
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);
		if ("jpg".equalsIgnoreCase(suffix) || "png".equalsIgnoreCase(suffix)) {
			try {
				ImageIO.write(image, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Error: filename must end in .jpg or .png");
		}
	}

	/**
	  * Opens a save dialog box when the user selects "Save As" from the menu.
	  */
	@Override
	public void actionPerformed(final ActionEvent e) {
		FileDialog chooser = new
			FileDialog (
				frame, "Use a .png or .jpg extension", FileDialog.SAVE);
		chooser.setVisible(true);
		if (chooser.getFile() != null) {
			save(chooser.getDirectory() + File.separator + chooser.getFile());
		}
	}
}

