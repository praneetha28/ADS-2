/******************************************************************************
 *  Compilation:  javac StdOut.java
 *  Execution:    java StdOut
 *  Dependencies: none
 *
 *  Writes data of various types to standard output.
 *
 ******************************************************************************/

import java.io.OutputStreamWriter;
/**.
 * { item_description }
 */
import java.io.PrintWriter;
/**.
 * { item_description }
 */
import java.io.UnsupportedEncodingException;
/**.
 * { item_description }
 */
import java.util.Locale;
/**.
 *  This class provides methods for printing strings and numbers to standard output.
 *  <p>
 *  <b>Getting started.</b>
 *  To use this class, you must have {@code StdOut.class} in your
 *  Java classpath. If you used our autoinstaller, you should be all set.
 *  Otherwise, either download
 *  <a href = "https://introcs.cs.princeton.edu/java/code/stdlib.jar">stdlib.jar</a>
 *  and add to your Java classpath or download
 *  <a href = "https://introcs.cs.princeton.edu/java/stdlib/StdOut.java">StdOut.java</a>
 *  and put a copy in your working directory.
 *  <p>
 *  Here is an example program that uses {@code StdOut}:
 *  <pre>
 *   public class TestStdOut {
 *       public static void main(String[] args) {
 *           int a = 17;
 *           int b = 23;
 *           int sum = a + b;
 *           StdOut.println("Hello, World");
 *           StdOut.printf("%d + %d = %d\n", a, b, sum);
 *       }
 *   }
 *  </pre>
 *  <p>
 *  <b>Differences with System.out.</b>
 *  The behavior of {@code StdOut} is similar to that of {@link System#out},
 *  but there are a few technical differences:
 *  <ul>
 *  <li> {@code StdOut} coerces the character-set encoding to UTF-8,
 *       which is a standard character encoding for Unicode.
 *  <li> {@code StdOut} coerces the locale to {@link Locale#US},
 *       for consistency with {@link StdIn}, {@link Double#parseDouble(String)},
 *       and floating-point literals.
 *  <li> {@code StdOut} <em>flushes</em> standard output after each call to
 *       {@code print()} so that text will appear immediately in the terminal.
 *  </ul>
 *  <p>
 *  <b>Reference.</b>
 *  For additional documentation,
 *  see <a href="https://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 *  <em>Computer Science: An Interdisciplinary Approach</em>
 *  by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public final class StdOut {
    /**.
     * { var_description }
     */
    private static final String CHARSET_NAME = "UTF-8";
    /**.
     * { var_description }
     */
    private static final Locale LOCALE = Locale.US;
    /**.
     * { var_description }
     */
    private static PrintWriter out;
    /**.
     * { item_description }
     */
    static {
        try {
            out = new PrintWriter(new
                OutputStreamWriter(System.out, CHARSET_NAME), true);
        }
        catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
    /**.
     * Constructs the object.
     */
    private StdOut() { }
   /**.
    * { function_description }
    */
    public static void close() {
        out.close();
    }
    /**.
     * { function_description }
     */
    public static void println() {
        out.println();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final Object x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final boolean x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final char x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final double x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final float x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final int x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final long x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final short x) {
        out.println(x);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void println(final byte x) {
        out.println(x);
    }
    /**.
     * { function_description }
     */
    public static void print() {
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final Object x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final boolean x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final char x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final double x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final float x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final int x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final long x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final short x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     */
    public static void print(final byte x) {
        out.print(x);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      format  The format
     * @param      args    The arguments
     */
    public static void printf(final String format,
        final Object... args) {
        out.printf(LOCALE, format, args);
        out.flush();
    }
    /**.
     * { function_description }
     *
     * @param      locale  The locale
     * @param      format  The format
     * @param      args    The arguments
     */
    public static void printf(final Locale locale,
        final String format, final Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }
}
