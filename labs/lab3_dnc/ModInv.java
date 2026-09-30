public class ModInv {
        
    // Returns b in [0, m) such that (a * b) % m == 1.
    // Requires m > 1 and gcd(a, m) == 1.
    public static int modularInv(int a, int m) {
        if (m <= 1) throw new IllegalArgumentException("m must be > 1");

        long oldR = ((a % m) + m) % m, r = m;  // remainders
        long oldX = 1, x = 0;                  // coefficients of a

        while (r != 0) {
            long q = oldR / r;

            long tmpR = oldR - q * r;
            oldR = r;
            r = tmpR;

            long tmpX = oldX - q * x;
            oldX = x;
            x = tmpX;
        }

        if (oldR != 1) throw new ArithmeticException("a and m are not coprime");

        return (int) (((oldX % m) + m) % m);  // normalize into [0, m)
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: ModInv a m");
            System.out.println(" > computes the modular inverse of a mod m");
            System.out.println(" > the value b where (a*b) mod m == 1");
            System.out.println(" a and m must be positive integers");
            return;
        }
        int a = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        System.out.printf("Inverse of %d mod %d is: %d\n", a, m, modularInv(a, m));
    }
}
