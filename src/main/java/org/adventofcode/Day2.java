package org.adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day2 {

    private final String filePathString;

    public Day2(String filePathString) {
        this.filePathString = filePathString;
    }

    public int total(Map<Colour, Integer> cubes) {
        try (var lines = Files.lines(Paths.get(filePathString))) {
            return sumGameLines(lines, cubes);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Map<Colour, Integer>> getMinimumCubesList() {
        try (var lines = Files.lines(Paths.get(filePathString))) {
            return getMinimumCubesList(lines);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int calculatePowers(List<Map<Colour, Integer>> minimumCubes) {
        return minimumCubes.stream().map(cubes -> cubes.values().stream().reduce(1, (a, b) -> a * b)).reduce(0, Integer::sum);
    }

    public int sumGameLines(Stream<String> lines, Map<Colour, Integer> cubes) {
        return lines.map(Game::parseGameLine)
                .filter(game -> isPossibleGame(game, cubes))
                .map(Game::getGameId)
                .reduce(0, Integer::sum);
    }

    public List<Map<Colour, Integer>> getMinimumCubesList(Stream<String> lines) {
        List<Map<Colour, Integer>> minimumCubes = new ArrayList<>();
        lines.map(Game::parseGameLine)
                .forEach(game -> {
                    Map<Colour, Integer> minimumCube = new EnumMap<>(Colour.class);
                    game.rounds.forEach(round -> round.forEach((colour, amount) -> {
                        if (minimumCube.containsKey(colour)) {
                            minimumCube.put(colour, Math.max(minimumCube.get(colour), amount));
                        } else {
                            minimumCube.put(colour, amount);
                        }
                    }));
                    minimumCubes.add(minimumCube);
                });
        return minimumCubes;
    }

    private boolean isPossibleGame(Game game, Map<Colour, Integer> cubes) {
        return game.rounds.stream().allMatch(round ->
                                                     round.entrySet().stream()
                                                             .allMatch(entry -> cubes.get(entry.getKey()) >= entry.getValue()));
    }

    public enum Colour {
        BLUE("blue"),
        RED("red"),
        GREEN("green");

        private final String colourName;

        Colour(String colourName) {
            this.colourName = colourName;
        }

        static Colour getByColourName(String colourName) {
            for (Colour colour : Colour.values()) {
                if (colour.colourName.equals(colourName)) {
                    return colour;
                }
            }
            throw new IllegalArgumentException("No colour found for colour name: " + colourName);
        }
    }

    private static class Game {
        final int gameId;
        final List<Map<Colour, Integer>> rounds;

        public Game(int gameId, List<Map<Colour, Integer>> rounds) {
            this.gameId = gameId;
            this.rounds = rounds;
        }

        public int getGameId() {
            return gameId;
        }

        static Game parseGameLine(String line) {
            // This assumes format Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            // and that it will always be in correct format
            var gameId = Integer.parseInt(line.split(":")[0].split(" ")[1]);
            var rounds = line.split(":")[1].split(";");
            List<Map<Colour, Integer>> roundList = new ArrayList<>();
            for (String round : rounds) {
                Map<Colour, Integer> map = new EnumMap<>(Colour.class);
                String[] roundParts = round.trim().split(",");
                for (String roundPart : roundParts) {
                    String[] roundPartParts = roundPart.trim().split(" ");
                    map.put(Colour.getByColourName(roundPartParts[1]), Integer.parseInt(roundPartParts[0]));
                }
                roundList.add(map);
            }
            return new Game(gameId, roundList);
        }
    }
}
