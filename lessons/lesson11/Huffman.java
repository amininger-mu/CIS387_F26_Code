import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Huffman {
    static class Encoding implements Comparable<Encoding> {
        final int frequency;
        Map<Character, String> codes;

        // Create a blank encoding for a single character
        public Encoding(char c, int frequency) {
            this.frequency = frequency;
            this.codes = new HashMap<>();
            this.codes.put(c, "");
        }

        // Create a new encoding which merges the two given
        public Encoding(Encoding e1, Encoding e2) {
            frequency = e1.frequency + e2.frequency;
            this.codes = new HashMap<>(e1.codes);
            this.codes.putAll(e2.codes);
        }

        // Puts the given prefix in front of all codes
        public void prefix(String s) {
            for (var e : codes.entrySet()) {
                codes.put(e.getKey(), s + e.getValue());
            }
        }

        // Compare encodings based on frequency
        public int compareTo(Encoding e) {
            return Integer.compare(frequency, e.frequency);
        }
    }

    // Returns a histogram of chars appearing in the text, and their counts
    public static Map<Character, Integer> countFrequency(String text) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : text.toCharArray()) {
            int count = counts.getOrDefault(c, 0);
            counts.put(c, count + 1);
        }
        return counts;
    }

    public static String compress(String text) {
        // Get frequency of each character in text
        Map<Character, Integer> counts = countFrequency(text);

        // Initialize priority queue - every character with its frequency
        PriorityQueue<Encoding> encodings = new PriorityQueue<>();

        for (var e : counts.entrySet()) {
            // key = character, value = count
            encodings.add(new Encoding(e.getKey(), e.getValue()));
        }

        while (encodings.size() > 1) {
            // Get two smallest items
            Encoding e1 = encodings.poll();
            Encoding e2 = encodings.poll();

            // Prefix with 0 and 1
            e1.prefix("0");
            e2.prefix("1");

            // Merge the encodings
            encodings.add(new Encoding(e1, e2));
        }

        // final encoding contains everything
        Encoding huffman = encodings.poll();

        // print encodings
        for (var e : huffman.codes.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }

        // Map each character in the text to its encoding
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            result.append(huffman.codes.get(c));
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        String text = "abracadabra";
        //String text = Files.readString(Path.of("verse.txt"));
        //String text = Files.readString(Path.of("genesis.txt"));
        String compressed = compress(text);
        System.out.println("Original Text: " + text.length()*8 + " bits");
        System.out.println("Compressed   : " + compressed.length() + " bits");
    }
}
