package org.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day4 {

    private Day4() {
    }

    public static int totalPoints(Stream<String> lines) {
        return lines.map(Card::new).mapToInt(Card::points).sum();
    }

    public static int totalPoints(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(Card::new).mapToInt(Card::points).sum();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Card {
        List<Integer> winningNumbers;
        List<Integer> numbers;

        public Card(String cardString) {
            String[] cardStringParts = cardString.split(":")[1].split("\\|");
            winningNumbers = Arrays.stream(cardStringParts[0].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            numbers = Arrays.stream(cardStringParts[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
        }

        public int points() {
            int count = 0;
            for (int number : numbers) {
                if (winningNumbers.contains(number)) {
                    count++;
                }
            }
            return count > 1 ?  (int) Math.pow(2, (double)count - 1) : count;
        }
    }

}
