import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaxSubarray {

    public static int maxSubArraySlow(int[] arr) {
        int max = Integer.MIN_VALUE;
        // Consider every subarray starting at each index i
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    public static int maxSubArray(int[] arr) {
        return maxSubArray(arr, 0, arr.length-1);
    }

    private static int maxSubArray(int[] arr, int lo, int hi) {
        if (hi - lo == 0) {
            return arr[lo];
        }

        int mid = (lo + hi) / 2;
        int curMax = maxSubArray(arr, lo, mid);
        curMax = Math.max(curMax, maxSubArray(arr, mid+1, hi));
        curMax = Math.max(curMax, maxMidArray(arr, lo, mid, hi));
        return curMax;
    }

    // Returns the max subarray sum that crosses the midpoint
    private static int maxMidArray(int[] arr, int lo, int mid, int hi) {
        int maxSum = arr[mid];

        // Find max subarray sum scanning left
        int leftSum = maxSum;
        for (int i = mid-1; i >= lo; i--) {
            leftSum += arr[i];
            maxSum = Math.max(leftSum, maxSum);
        }

        // Find max subarray sum scanning right
        int rightSum = maxSum;
        for (int i = mid+1; i <= hi; i++) {
            rightSum += arr[i];
            maxSum = Math.max(rightSum, maxSum);
        }

        return maxSum;
    }

    public static int runTest(int n, boolean fast) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            // generate random value from -1000 to 1000
            arr[i] = -1000 + (int)(Math.random() * 2000); 
        }

        if (fast) {
            return maxSubArray(arr);
        } else {
            return maxSubArraySlow(arr);
        }
    }

    public static void main(String[] args) {
        boolean fast = true;
        if (args.length > 0 && args[0].equals("-s")) {
            fast = false;
        }

        List<Integer> nums = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            nums.add(scanner.nextInt());
        }
        // Convert list to int[]
        int[] arr = nums.stream().mapToInt(Integer::intValue).toArray();

        if (fast) {
            System.out.println(maxSubArray(arr));
        } else {
            System.out.println(maxSubArraySlow(arr));
        }
    }
}
