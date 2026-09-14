package sorting;
/******************************************************************************
 *  Compilation:  javac ShellBars.java
 *  Execution:    java ShellBars N
 *  Dependencies: StdDraw.java
 *
 *  Shell sort n random real numbers between 0 and 1, visualizing
 *  the results by ploting bars with heights proportional to the values.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class ShellBars {
    static int DELAY = 1000;


    public static void sort(double[] a) {
        int n = a.length;
        int k = 1;
        int h = 1;
        while (h < n/3) {
            h = 3*h + 1;
            k++;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int j = i;
                show(a, i, j, h);
                while (j >= h && less(a[j], a[j-h])) {
                    exch(a, j, j-h);
                    j -= h;
                    show(a, i, j, h);
                }
            }
            h = h/3;
        }
    }

    private static void show(double[] a, int i, int j, int h) {
        StdDraw.clear();
        
        // Draw all bars in gray
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < a.length; k++)
            StdDraw.filledRectangle(k, a[k]/2, 0.25, a[k]/2);

        // Draw bars in blue that are less than j in same group
        for (int k = j-h; k >= 0; k -= h) {
            StdDraw.setPenColor(StdDraw.BOOK_BLUE);
            StdDraw.filledRectangle(k, a[k]/2, 0.25, a[k]/2);
        }

        // Draw j in red
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(j, a[j]/2, 0.25, a[j]/2);

        // Draw values that j swapped with in black
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = j+h; k <= i; k += h)
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
        double[] a = new double[n];
        for (int i = 0; i < n; i++)
          a[i] = StdRandom.uniformDouble(0.0, 1.0);

        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(1200, 400);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setYscale(-0.1, 1.1);
        StdDraw.setPenRadius(0.006);
        sort(a);
        StdDraw.show();
    }
}
