package lesson2_adts;

public class Point2D {
    private double x;
    private double y;

    public Point2D() {
        x = 0.0;
        y = 0.0;
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return String.format("(%.3f, %.3f)", x, y);
    }


    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Point2D)) return false;
        Point2D p2 = (Point2D)obj;
        return this.x == p2.x && this.y == p2.y;
    }



    public static void main(String[] args) {
        Point2D p1 = new Point2D(1.0, -1.0);
        Point2D p2 = new Point2D(2.0, 4.5);
        Point2D p3 = new Point2D(1.0, -1.0);

        // All 3 call toString()
        System.out.println(p1);
        System.out.println("P2 = " + p2);
        System.out.printf("P3 = %s\n", p3);


        if (p1.equals(p2)) {
            System.out.println("P1 and P2 are equal");
        }
        if (p1.equals(p3)) {
            System.out.println("P1 and P3 are equal");
        }
    }
}
