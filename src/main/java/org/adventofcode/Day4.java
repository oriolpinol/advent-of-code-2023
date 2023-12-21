package org.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {


    private Day4() {
    }

    public static long totalCards(Stream<String> lines) {
        Map<Integer, List<Card>> cards = lines.map(Card::new).collect(Collectors.groupingBy(Card::getCardNumber));
        for (Map.Entry<Integer, List<Card>> entry : cards.entrySet()) {
            entry.getValue().forEach(card -> {
                var points = card.matchingNumbers();
                for (int i = 0; i < points; i++) {
                    var list = cards.get(entry.getKey() + i + 1);
                    if (list != null) {
                        list.add(list.get(0));
                    }
                }
            });
        }
        return cards.values().stream().mapToLong(List::size).sum();
    }

    public static long totalCards(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return totalCards(lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        int cardNumber;
        List<Integer> winningNumbers;
        List<Integer> numbers;

        public Card(String cardString) {
            final String[] split = cardString.split(":");
            cardNumber = Integer.parseInt(split[0].replaceAll("[^\\d]", ""));
            String[] cardStringParts = split[1].split("\\|");
            winningNumbers = Arrays.stream(cardStringParts[0].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            numbers = Arrays.stream(cardStringParts[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
        }

        public int points() {
            int count = matchingNumbers();
            return count > 1 ?  (int) Math.pow(2, (double)count - 1) : count;
        }

        public int matchingNumbers() {
            int count = 0;
            for (int number : numbers) {
                if (winningNumbers.contains(number)) {
                    count++;
                }
            }
            return count;
        }

        public int getCardNumber() {
            return cardNumber;
        }
    }

}
