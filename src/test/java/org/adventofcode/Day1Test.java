package org.adventofcode;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    Stream<String> lines = ("1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet").lines();

    @org.junit.jupiter.api.Test
    void total() {
        var day1 = new Day1("src/main/resources/day1.txt");
        assertEquals(53651, day1.total());
    }

    @org.junit.jupiter.api.Test
    void sumCalibrationValues() {
        var day1 = new Day1("src/main/resources/day1.txt");
        assertEquals(142, day1.sumCalibrationValues(lines));
    }

}