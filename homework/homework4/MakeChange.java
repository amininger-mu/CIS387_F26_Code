/**************************************************************************
 *  Class: MakeChange.java
 * 
 *  Student: STUDENT NAME HERE
 * 
 *  Algorithm to find the fewest number of coins needed to make a given amount
 * 
 *  Example: 4 coins (1, 5, 10, 25), to make 97 cents you need 7 coins
 *     (3x25, 2x10, 2x1)
 * 
 *  Implement the function: int fewestCoinsNeeded(int amount, int[] coins)
 * 
 *  To pass data from a file, do: 'cat change_test1.txt | java MakeChange.java'
 ***********************************************************************/

import java.util.Scanner;

public class MakeChange {

	public static int fewestCoinsNeeded(int amount, int[] coins) {

		return 0;
	}

    /*****
     * MakeChange::main
     * 
     * Reads from standard in:
	 *   1 int: amount of cents
	 *   1 int: number of types of coins (n)
	 *   n ints: value of each coin
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

		// 1 int: amount of cents
		int amount = scanner.nextInt();

		// 1 int: number of coins
		int nCoins = scanner.nextInt();
		int[] coins = new int[nCoins];

		// n ints: value of each coin
		for (int i = 0; i < nCoins; i++) {
			coins[i] = scanner.nextInt();
		}

		int num = fewestCoinsNeeded(amount, coins);
		System.out.printf("Fewest coins needed to make %d cents: %d\n", amount, num);

        scanner.close();
    }
}

