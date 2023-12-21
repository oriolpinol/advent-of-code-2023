package org.adventofcode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    Stream<String> lines = ("""
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""").lines();
    @Test
    void sumCalibrationValues() {
        var day2 = new Day2("src/main/resources/day2.txt");
        assertEquals(8, day2.sumGameLines(lines, Map.of(Day2.Colour.RED, 12, Day2.Colour.GREEN, 13, Day2.Colour.BLUE, 14)));
    }

    @Test
    void totalTest() {
        var day2 = new Day2("src/main/resources/day2.txt");
        assertEquals(2486, day2.total(Map.of(Day2.Colour.RED, 12, Day2.Colour.GREEN, 13, Day2.Colour.BLUE, 14)));
    }

    @Test
    void getMinimumCubesTest() {
        var day2 = new Day2("src/main/resources/day2.txt");
        assertEquals(List.of(Map.of(Day2.Colour.BLUE, 6, Day2.Colour.RED, 4, Day2.Colour.GREEN, 2), Map.of(Day2.Colour.BLUE, 4, Day2.Colour.RED, 1, Day2.Colour.GREEN, 3), Map.of(Day2.Colour.BLUE, 6, Day2.Colour.RED, 20, Day2.Colour.GREEN, 13), Map.of(Day2.Colour.BLUE, 15, Day2.Colour.RED, 14, Day2.Colour.GREEN, 3), Map.of(Day2.Colour.BLUE, 2, Day2.Colour.RED, 6, Day2.Colour.GREEN, 3)), day2.getMinimumCubesList(lines));
    }

    @Test
    void calculatePowersTest() {
        var day2 = new Day2("src/main/resources/day2.txt");
        assertEquals(87984, day2.calculatePowers(day2.getMinimumCubesList()));
    }


}