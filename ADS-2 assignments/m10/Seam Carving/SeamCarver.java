/**.
 * { item_description }
 */
import java.awt.Color;
/**.
 * Class for seam carver.
 */
public class SeamCarver {
    /**.
     * { var_description }
     */
    private Picture picture;
    /**.
     * { var_description }
     */
    private static final int THOUSAND = 1000;
    // create a seam carver object based on the given picture
    /**.
     * Constructs the object.
     *
     * @param      pic   The picture
     */
    public SeamCarver(final Picture pic) {
        this.picture = pic;
        if (picture == null) {
            System.out.println("picture is null");
        }
    }
    // current picture
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Picture picture() {
        return picture;
    }
    // width of current picture
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int width() {
        return picture.width();
    }

    // height of current picture
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int height() {
        return picture.height();
    }
    // energy of pixel at column x and row y
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double energy(final int x, final int y) {
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
            return THOUSAND;
        }
        return Math.sqrt(calculatesquare(picture.get(x - 1, y),
            picture.get(x + 1, y)) + calculatesquare(
            picture.get(x, y - 1), picture.get(x, y + 1)));
    }
    /**.
     * { function_description }
     *
     * @param      c1    The c 1
     * @param      c2    The c 2
     *
     * @return     { description_of_the_return_value }
     */
    private double calculatesquare(final Color c1, final Color c2) {
        double red = c1.getRed() - c2.getRed();
        double green = c1.getGreen() - c2.getGreen();
        double blue = c1.getBlue() - c2.getBlue();
        return red * red + green * green + blue * blue;
    }
    // sequence of indices for horizontal seam
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findHorizontalSeam() {
        return new int[0];
    }

    // sequence of indices for vertical seam
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findVerticalSeam() {
        return new int[0];
    }
    // remove horizontal seam from current pictureture
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public void removeHorizontalSeam(final int[] seam) {
        return;
    }
    // remove vertical seam from current picture
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public void removeVerticalSeam(final int[] seam) {
        return;
    }
}
