package org.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day1 {

    private final String filePathString;

    public Day1(String filePathString) {
        this.filePathString = filePathString;
    }

    private int getCalibrationValueForLine(String line) {
        // Remove all non-numeric characters
        var lineWithoutNonNumericCharacters = line.replaceAll("[^\\d-]", "");
        // Take the first and the last character and put them together in a number
        var firstCharacter = lineWithoutNonNumericCharacters.charAt(0);
        var lastCharacter = lineWithoutNonNumericCharacters.charAt(lineWithoutNonNumericCharacters.length() - 1);
        return Integer.parseInt(firstCharacter + "" + lastCharacter);
    }

    public int total() {
        try (var lines = Files.lines(Paths.get(filePathString))) {
            return sumCalibrationValues(lines);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int sumCalibrationValues(Stream<String> lines) {
        return lines.map(this::getCalibrationValueForLine)
                .reduce(0, Integer::sum);
    }

}
