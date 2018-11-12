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
 *  This class provides methods for manipulating individual pixels of
 *  an image using the RGB color format. The alpha component (for transparency)
 *  is not currently supported.
 *  The original image can be read from a {@code PNG}, {@code GIF},
 *  or {@code JPEG} file or the user can create a blank image of a given dimension.
 *  This class includes methods for displaying the image in a window on
 *  the screen or saving it to a file.
 *  <p>
 *  Pixel (<em>col</em>, <em>row</em>) is column <em>col</em> and row <em>row</em>.
 *  By default, the origin (0, 0) is the pixel in the top-left corner,
 *  which is a common convention in image processing.
 *  The method {@link #setOriginLowerLeft()} change the origin to the lower left.
 *  <p>
 *  The {@code get()} and {@code set()} methods use {@link Color} objects to get
 *  or set the color of the specified pixel.
 *  The {@code getRGB()} and {@code setRGB()} methods use a 32-bit {@code int}
 *  to encode the color, thereby avoiding the need to create temporary
 *  {@code Color} objects. The red (R), green (G), and blue (B) components
 *  are encoded using the least significant 24 bits.
 *  Given a 32-bit {@code int} encoding the color, the following code extracts
 *  the RGB components:
 * <blockquote><pre>
 *  int r = (rgb >> 16) & 0xFF;
 *  int g = (rgb >>  8) & 0xFF;
 *  int b = (rgb >>  0) & 0xFF;
 *  </pre></blockquote>
 *  Given the RGB components (8-bits each) of a color,
 *  the following statement packs it into a 32-bit {@code int}:
 * <blockquote><pre>
 *  int rgb = (r << 16) + (g << 8) + (b << 0);
 * </pre></blockquote>
 *  <p>
 *  A <em>W</em>-by-<en>H</em> picture uses ~ 4 <em>W H</em> bytes of memory,
 *  since the color of each pixel is encoded as a 32-bit <code>int</code>.
 *  <p>
 *  For additional documentation, see
 *  <a href="https://introcs.cs.princeton.edu/31datatype">Section 3.1</a> of
 *  <i>Computer Science: An Interdisciplinary Approach</i>
 *  by Robert Sedgewick and Kevin Wayne.
 *  See {@link GrayscalePicture} for a version that supports grayscale images.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class Picture implements ActionListener {
    /**.
     * { var_description }
     */
    private BufferedImage image;
    /**.
     * { var_description }
     */
    private JFrame frame;
    /**.
     * { var_description }
     */
    private String filename;
    /**.
     * { var_description }
     */
    private boolean isOriginUpperLeft = true;
    /**.
     * { var_description }
     */
    private final int width, height;
   /**.
     * Creates a {@code width}-by-{@code height} picture, with {@code width} columns
     * and {@code height} rows, where each pixel is black.
     *
     * @param width the width of the picture
     * @param height the height of the picture
     * @throws IllegalArgumentException if {@code width} is negative
     * @throws IllegalArgumentException if {@code height} is negative
     */
    public Picture(final int width, final int height) {
        if (width  < 0) {
            throw new IllegalArgumentException(
                "width must be non-negative");
        }
        if (height < 0) {
            throw new IllegalArgumentException(
                "height must be non-negative");
        }
        this.width  = width;
        this.height = height;
        image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
        // set to TYPE_INT_ARGB here and in next constructor to support transparency
    }
   /**.
     * Creates a new picture that is a deep copy of the argument picture.
     *
     * @param  picture the picture to copy
     * @throws IllegalArgumentException if {@code picture} is {@code null}
     */
    public Picture(final Picture picture) {
        if (picture == null) {
            throw new IllegalArgumentException(
                "constructor argument is null");
        }
        width  = picture.width();
        height = picture.height();
        image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
        filename = picture.filename;
        isOriginUpperLeft = picture.isOriginUpperLeft;
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                image.setRGB(col, row,
                    picture.image.getRGB(col, row));
            }
        }
    }

   /**.
     * Creates a picture by reading an image from a file or URL.
     *
     * @param  filename the name of the file (.png, .gif, or .jpg) or URL.
     * @throws IllegalArgumentException if cannot read image
     * @throws IllegalArgumentException if {@code filename} is {@code null}
     */
    public Picture(final String filename) {
        if (filename == null) {
            throw new IllegalArgumentException(
                "constructor argument is null");
        }
        this.filename = filename;
        try {
            // try to read from file in working directory
            File file = new File(filename);
            if (file.isFile()) {
                image = ImageIO.read(file);
            } else {
                URL url = getClass().getResource(filename);
                if (url == null) {
                    url = new URL(filename);
                }
                image = ImageIO.read(url);
            }
            if (image == null) {
                throw new IllegalArgumentException(
                    "could not read image file: " + filename);
            }
            width  = image.getWidth(null);
            height = image.getHeight(null);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException(
                "could not open image file: " + filename, ioe);
        }
    }
   /**.
     * Creates a picture by reading the image from a PNG, GIF, or JPEG file.
     *
     * @param file the file
     * @throws IllegalArgumentException if cannot read image
     * @throws IllegalArgumentException if {@code file} is {@code null}
     */
    public Picture(final File file) {
        if (file == null) {
            throw new IllegalArgumentException(
                "constructor argument is null");
        }
        try {
            image = ImageIO.read(file);
        } catch (IOException ioe) {
            throw new IllegalArgumentException(
                "could not open file: " + file, ioe);
        }
        if (image == null) {
            throw new IllegalArgumentException(
                "could not read file: " + file);
        }
        width  = image.getWidth(null);
        height = image.getHeight(null);
        filename = file.getName();
    }

   /**.
     * Returns a {@link JLabel} containing this picture, for embedding in a {@link JPanel},
     * {@link JFrame} or other GUI widget.
     *
     * @return the {@code JLabel}
     */
    public JLabel getJLabel() {
        if (image == null) return null;
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }
   /**.
    * Sets the origin upper left.
    */
    public void setOriginUpperLeft() {
        isOriginUpperLeft = true;
    }
   /**.
    * Sets the origin lower left.
    */
    public void setOriginLowerLeft() {
        isOriginUpperLeft = false;
    }
   /**.
   * { function_description }
   */
    public void show() {
        if (frame == null) {
            frame = new JFrame();
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuBar.add(menu);
            JMenuItem menuItem1 = new JMenuItem(
                " Save...   ");
            menuItem1.addActionListener(this);
            menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            menu.add(menuItem1);
            frame.setJMenuBar(menuBar);
            frame.setContentPane(getJLabel());
            // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (filename == null) {
                frame.setTitle(width + "-by-" + height);
            } else {
                frame.setTitle(filename);
            }
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }
        frame.repaint();
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int height() {
        return height;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int width() {
        return width;
    }
    /**.
     * { function_description }
     *
     * @param      row   The row
     */
    private void validateRowIndex(final int row) {
        if (row < 0 || row >= height()) {
            throw new IllegalArgumentException(
          "row index must be between 0 and " + (height() - 1) + ": " + row);
        }
    }
    /**.
     * { function_description }
     *
     * @param      col   The col
     */
    private void validateColumnIndex(final int col) {
        if (col < 0 || col >= width()) {
            throw new IllegalArgumentException(
        "column index must be between 0 and " + (width() - 1) + ": " + col);
        }
    }
    /**.
     * { function_description }
     *
     * @param      col   The col
     * @param      row   The row
     *
     * @return     { description_of_the_return_value }
     */
    public Color get(final int col, final int row) {
        validateColumnIndex(col);
        validateRowIndex(row);
        int rgb = getRGB(col, row);
        return new Color(rgb);
    }
    /**.
     * Gets the rgb.
     *
     * @param      col   The col
     * @param      row   The row
     *
     * @return     The rgb.
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
    /**.
     * { function_description }
     *
     * @param      col    The col
     * @param      row    The row
     * @param      color  The color
     */
    public void set(final int col, final int row, final Color color) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (color == null) {
            throw new IllegalArgumentException(
                "color argument is null");
        }
        int rgb = color.getRGB();
        setRGB(col, row, rgb);
    }
    /**.
     * Sets the rgb.
     *
     * @param      col   The col
     * @param      row   The row
     * @param      rgb   The rgb
     */
    public void setRGB(
        final int col, final int row, final int rgb) {
        validateColumnIndex(col);
        validateRowIndex(row);
        if (isOriginUpperLeft) {
            image.setRGB(col, row, rgb);
        } else {
            image.setRGB(col, height - row - 1, rgb);
        }
    }
    /**.
     * { function_description }
     *
     * @param      other  The other
     *
     * @return     { description_of_the_return_value }
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
        if (this.width() != that.width()) {
            return false;
        }
        if (this.height() != that.height()) {
            return false;
        }
        for (int col = 0; col < width(); col++) {
            for (int row = 0; row < height(); row++) {
                if (this.getRGB(col, row) != that.getRGB(col, row)) {
                    return false;
                }
            }
        }
        return true;
    }
    /**.
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(width +"-by-" + height
            + " picture (RGB values given in hex)\n");
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
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int hashCode() {
        throw new UnsupportedOperationException(
            "hashCode() is not supported because pictures are mutable");
    }
    /**.
     * { function_description }
     *
     * @param      name  The name
     */
    public void save(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(
                "argument to save() is null");
        }
        save(new File(name));
        filename = name;
    }
    /**.
     * { function_description }
     *
     * @param      file  The file
     */
    public void save(final File file) {
        if (file == null) {
            throw new IllegalArgumentException(
                "argument to save() is null");
        }
        filename = file.getName();
        if (frame != null) {
            frame.setTitle(filename);
        }
        String suffix = filename.substring(
            filename.lastIndexOf('.') + 1);
        if ("jpg".equalsIgnoreCase(suffix)
            || "png".equalsIgnoreCase(suffix)) {
            try {
                ImageIO.write(image, suffix, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }
    /**.
     * { function_description }
     *
     * @param      e     { parameter_description }
     */
    public void actionPerformed(final ActionEvent e) {
        FileDialog chooser = new FileDialog(frame,
                "Use a .png or .jpg extension", FileDialog.SAVE);
        chooser.setVisible(true);
        if (chooser.getFile() != null) {
            save(chooser.getDirectory() + File.separator + chooser.getFile());
        }
    }
}
