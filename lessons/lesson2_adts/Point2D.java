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


    public static void main(String[] args) {
        Point2D p1 = new Point2D(1.0, -1.0);
        Point2D p2 = new Point2D(2.0, 4.5);
        Point2D p3 = new Point2D(1.0, -1.0);

        System.out.println(p1);
    }

}
