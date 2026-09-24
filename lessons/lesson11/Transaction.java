import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class Transaction implements Comparable<Transaction>{
    private final int userId;
    private final double amount;
    private final Instant timestamp;

    public Transaction(int userId, double amount, Instant timestamp) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public int getUserId() { return userId; }
    public double getAmount() { return amount; }
    public Instant getTimestamp() { return timestamp; }

	public static int compareUserIds(Transaction t1, Transaction t2) {
		return Integer.compare(t1.userId, t2.userId);
	}
	public static int compareAmounts(Transaction t1, Transaction t2) {
		return Double.compare(t1.amount, t2.amount);
	}
	public static int compareAmountsDescending(Transaction t1, Transaction t2) {
		return -Double.compare(t1.amount, t2.amount);
	}
	public static int compareTimestamps(Transaction t1, Transaction t2) {
		return t1.timestamp.compareTo(t2.timestamp);
	}

    public int compareTo(Transaction t2) {
        return timestamp.compareTo(t2.timestamp);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "Transaction{userId=%d, amount=%.2f, timestamp=%s}",
                userId, amount, timestamp);
    }

    /** Reads a CSV file produced by generateCsv and returns its transactions. */
    public static List<Transaction> readCsv(String filename) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        Path path = Paths.get(filename);

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // skip header
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Malformed line " + lineNumber + ": " + line);
                }
                try {
                    transactions.add(new Transaction(
                            Integer.parseInt(parts[0].trim()),
                            Double.parseDouble(parts[1].trim()),
                            Instant.parse(parts[2].trim())));
                } catch (RuntimeException e) {
                    System.err.println("Could not parse line " + lineNumber + ": " + line);
					System.err.println(e);
                }
            }
        }
        return transactions;
    }

    static class TimeOrder implements Comparator<Transaction> {
        public int compare(Transaction t1, Transaction t2) {
            return t1.timestamp.compareTo(t2.timestamp);
        }
    }

    public static void main(String[] args) throws IOException {
        String file = "transactions.csv";
        List<Transaction> transactions = readCsv(file);
        System.out.println("Read " + transactions.size() + " transactions:");

        transactions.sort(Transaction::compareTimestamps);

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
