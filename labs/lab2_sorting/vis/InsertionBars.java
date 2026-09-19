package sorting;
/******************************************************************************
 *  Compilation:  javac InsertionBars.java
 *  Execution:    java InsertionBars N
 *  Dependencies: StdDraw.java
 *
 *  Insertion sort n random real numbers between 0 and 1, visualizing
 *  the results by ploting bars with heights proportional to the values.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class InsertionBars {
    static int DELAY = 1000;

    public static void sort(double[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            show(a, i, j);
            while (j >= 1 && less(a[j], a[j-1])) {
                exch(a, j, j-1);
                j--;
                show(a, i, j);
            }
        }
    }

    private static void show(double[] a, int i, int j) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < a.length; k++)
            StdDraw.filledRectangle(k, a[k]/2, 0.25, a[k]/2);

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(j, a[j]/2, 0.25, a[j]/2);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = j+1; k <= i; k++)
            StdDraw.filledRectangle(k, a[k]/2, 0.25, a[k]/2);

        StdDraw.show();
        StdDraw.pause(DELAY);
    }

    private static boolean less(double v, double w) {
        return v < w;
    }

    private static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        if (args.length > 1) {
            DELAY = Integer.parseInt(args[1]);
        }

        StdDraw.setCanvasSize(1200, 400);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setYscale(-0.1, 1.1);

        StdDraw.setPenRadius(0.006);
        StdDraw.enableDoubleBuffering();

        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniformDouble(0.0, 1.0);
        sort(a);
    }
}
