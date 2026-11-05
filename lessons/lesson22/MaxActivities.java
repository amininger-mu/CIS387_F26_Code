import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class MaxActivities {

    public static record Activity(int start, int finish) { }


    public static List<Activity> maxActivities(Activity[] activities) {
        // Sort activities by finish time
        Arrays.sort(activities, (a1, a2) -> Integer.compare(a1.finish, a2.finish));

        var solution = new ArrayList<Activity>();

        solution.add(activities[0]);
        int minStart = activities[0].finish;

        // Check each possible k, seeing if it starts late enough to take
        for (int k = 1; k < activities.length; k++) {
            if (activities[k].start >= minStart) {
                solution.add(activities[k]);
                minStart = activities[k].finish;
            }
        }

        return solution;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        Activity[] activities = new Activity[N];
        for (int i = 0; i < N; i++) {
            activities[i] = new Activity(scanner.nextInt(), scanner.nextInt());
        }

        var solution = maxActivities(activities);

        System.out.println("Solution: " + solution.size());
        for (var activity : solution) {
            System.out.println(activity);
        }

        scanner.close();
    }

}
