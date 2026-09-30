public class ModExp {
    
    // computes a^b % c
    public static int modularExp(int a, int b, int c) {
        if (a == 0) return 0;
        if (b == 0) return 1;

        // If exp is even, split in two
        if (b % 2 == 0) {
            long y = modularExp(a, b/2, c);
            return (int)((y * y) % c);

        // If exp is odd, remove 1
        } else {
            long y = modularExp(a, b-1, c);
            return (int)((a * y) % c);
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: ModExp a b c");
            System.out.println(" > computes the value of a^b mod c");
            System.out.println(" > a, b, c  must be positive integers");
            return;
        }
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        System.out.printf("%d^%d mod %d = %d\n", a, b, c, modularExp(a, b, c));
    }
}

