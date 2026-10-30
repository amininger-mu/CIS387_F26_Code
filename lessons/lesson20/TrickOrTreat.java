import java.util.Scanner;

/***
 * Implementation of dynamic programming solutions to the problem
 *   of getting candy on Halloween from houses, 
 *   where you know the value of each house,
 * but the restriction that you cannot visit adjacent houses
 * 
 * Problem is defined as having N houses, and the value of each
 * 
 * What is the best way to visit houses to maximize value of candy
 *   without visiting two adjacent houses
 */

public class TrickOrTreat {

    /*************** TOP DOWN SOLUTION  *****************/
    public static int maxCandyTD(int[] houses) {
        int[] cache = new int[houses.length];
        return maxCandyTD(0, houses, cache);
    }
    private static int maxCandyTD(int i, int[] houses, int[] cache) {
        if (i >= houses.length) return 0;
        if (cache[i] != 0) return cache[i];

        int visit = houses[i] + maxCandyTD(i+2, houses, cache);
        int skip = maxCandyTD(i+1, houses, cache);
        int best = Math.max(visit, skip);

        cache[i] = best;
        return best;
    }

    /*************** BOTTOM UP SOLUTION  *****************/
    public static int maxCandyBU(int[] houses) {
        int[] cache = new int[houses.length+2];
        for (int i = houses.length-1; i >= 0; i--) {
            int visit = houses[i] + cache[i+2];
            int skip = cache[i+1];
            int best = Math.max(visit, skip);
            cache[i] = best;
        }
        return cache[0];
    }


    /********** BOTTOM UP - RETURN WHETHER TO VISIT EACH HOUSE **************/
    public static boolean[] maxCandySol(int[] houses) {
        int[] cache = new int[houses.length+2];
        boolean[] shouldVisit = new boolean[houses.length];
        for (int i = houses.length-1; i >= 0; i--) {
            int visit = houses[i] + cache[i+2];
            int skip = cache[i+1];
            if (visit >= skip) {
                shouldVisit[i] = true;
            }
            int best = Math.max(visit, skip);
            cache[i] = best;
        }

        return shouldVisit;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // int: number of houses
        int N = scanner.nextInt();
        int[] houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = scanner.nextInt();
        }

        System.out.printf("Max Candy = %d\n", maxCandyTD(houses));
        System.out.printf("Max Candy = %d\n", maxCandyBU(houses));

        boolean[] shouldVisit = maxCandySol(houses);

        int curHouse = 0;
        int total = 0;
        while(curHouse < houses.length) {
            if (shouldVisit[curHouse]) {
                System.out.printf("Visit %d = %d\n", curHouse, houses[curHouse]);
                total += houses[curHouse];
                curHouse += 2;
            } else {
                curHouse += 1;
            }
        }        
        System.out.println("Total Candy: " + total);

    }
}
