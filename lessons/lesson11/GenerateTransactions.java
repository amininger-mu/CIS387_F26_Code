import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateTransactions {
    private static final String HEADER = "userId,amount,timestamp";

    /**
     * Writes n random transactions to a CSV file.
     * userId: 100-10000 (inclusive), amount: 5.00-1000.00, timestamp: between
     * the start of the current year (UTC) and now.
     */
    public static void generateCsv(String filename, int n) throws IOException {
        if (n < 0) throw new IllegalArgumentException("n must be non-negative");

        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        Instant now = Instant.now();
        Instant startOfYear = LocalDate.now(ZoneOffset.UTC)
                .withDayOfYear(1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
        long startMillis = startOfYear.toEpochMilli();
        long endMillis = now.toEpochMilli();

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardCharsets.UTF_8)) {
            writer.write(HEADER);
            writer.newLine();
            for (int i = 0; i < n; i++) {
                int userId = rnd.nextInt(100, 120);
                // Work in cents so amounts are exact to 2 decimal places
                double amount = rnd.nextLong(500, 100001) / 100.0;
                Instant ts = Instant.ofEpochMilli(rnd.nextLong(startMillis, endMillis + 1));

                writer.write(String.format(Locale.ROOT, "%d,%.2f,%s", userId, amount, ts));
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
		int n = 100;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}

        String file = "transactions.csv";
        generateCsv(file, n);
    }
}
