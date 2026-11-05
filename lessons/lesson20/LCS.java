package lesson20;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LCS {
    static record State(int i, int j) { }

    static String LCS(String A, String B) {
        var cache = new HashMap<State, String>();
        return LCS(A, B, new State(0, 0), cache);
    }

    static String LCS(String A, String B, State S, Map<State, String> cache) {
        if (S.i >= A.length() || S.j >= B.length()) return "";

        if (cache.containsKey(S)) return cache.get(S);

        String result = "";
        if (A.charAt(S.i) == B.charAt(S.j)) {
            result = A.charAt(S.i) + LCS(A, B, new State(S.i+1, S.j+1), cache);
        } else {
            String skipA = LCS(A, B, new State(S.i+1, S.j), cache);
            String skipB = LCS(A, B, new State(S.i, S.j+1), cache);
            if (skipA.length() >= skipB.length()) {
                result = skipA;
            } else {
                result = skipB;
            }
        }

        cache.put(S, result);
        return result;
    }

    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.next();
        String B = scanner.next();
        System.out.println("A: " + A);
        System.out.println("B: " + B);
        System.out.println("LCS: " + LCS(A, B));
        scanner.close();
    }
}
