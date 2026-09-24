import java.util.Arrays;

public class HasDuplicate {
    public static boolean hasDuplicate(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasDuplicateFast(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < sorted.length-1; i++) {
            if (sorted[i] == sorted[i+1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean runTest(int n, boolean fast) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Sort.shuffle(arr);

        if (fast) {
            return hasDuplicateFast(arr);
        } else {
            return hasDuplicate(arr);
        }
    }
}
