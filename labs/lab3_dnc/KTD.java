import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

public class KTD {
    static record Preference(String name, int[] order) { }

    static Preference buildPref(String line) {
        line = line.trim();
        String[] parts = line.split(" ");
        if (parts.length < 2) return null;
        if (parts[1].length() < 10) return null;
        int[] order = new int[10];
        for (int i = 0; i < 10; i++) {
            order[i] = (parts[1].charAt(i) - 'A');
        }
        return new Preference(parts[0], order);
    }

    static int ktd(Preference p1, Preference p2) {
        return ktd(p1.order, p2.order);
    }

    static int ktd(int[] a1, int[] a2) {
        // record positions of each item in a1
        int[] pos = new int[a1.length];
        for (int i = 0; i < a1.length; i++) {
            pos[a1[i]] = i;
        }

        int[] arr = a2.clone();
        // remap a2 array 
        for (int i = 0; i < arr.length; i++) {
            arr[i] = pos[arr[i]];
        }
        int[] aux = arr.clone();
        return ktd(arr, aux, 0, arr.length-1);
    }

    static int ktd(int[] a, int[] aux, int lo, int hi) {
        if (hi - lo <= 0) return 0;

        int mid = (lo + hi) / 2;
        int count = ktd(a, aux, lo, mid) +
                    ktd(a, aux, mid+1, hi);
        return count + merge(a, aux, lo, mid, hi);
    }

    static int merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid+1;
        int count = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i] < aux[j]) a[k] = aux[i++];
            else {
                count += (mid - i) + 1;
                a[k] = aux[j++];
            }
        }
        return count;
    }




    

    public static void main(String[] args) {
        List<Preference> preferences = new ArrayList<Preference>();
        try (Scanner scanner = new Scanner(new File("preferences.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Preference pref = buildPref(line);
                if (pref != null) {
                    preferences.add(pref);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e);
        }

        Preference p1 = null;
        Preference p2 = null;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < preferences.size()-1; i++) {
            for (int j = i+1; j < preferences.size(); j++) {
                int dist = ktd(preferences.get(i), preferences.get(j));
                if (dist < minDist) {
                    minDist = dist;
                    p1 = preferences.get(i);
                    p2 = preferences.get(j);
                }
            }
        }
        System.out.println("Closest Preference: ");
        System.out.println(" - " + p1.name);
        System.out.println(" - " + p2.name);
		System.out.println(Arrays.toString(p1.order));
		System.out.println(Arrays.toString(p2.order));
        System.out.println(" Distance = " + minDist);

		int[] min = { 4,6,5,1,2,0,7,9,3,8 };
		Preference prof = new Preference("Mininger", min);

        minDist = Integer.MAX_VALUE;

        for (int i = 0; i < preferences.size()-1; i++) {
			int dist = ktd(preferences.get(i), prof);
			if (dist < minDist) {
				minDist = dist;
				p1 = preferences.get(i);
            }
        }
        System.out.println("Closest Preference: ");
        System.out.println(" - " + p1.name);
		System.out.println(Arrays.toString(p1.order));
		System.out.println(Arrays.toString(prof.order));
        System.out.println(" Distance = " + minDist);
    }
}
