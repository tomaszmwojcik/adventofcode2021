package com.github.tomaszmwojcik

import java.util.function.BiFunction

class Day7 {

    static def parseInput(InputStream input) {
        Scanner scanner = new Scanner(input)
        scanner.useDelimiter(/[,\n]/)
        List<Long> positions = []
        while(scanner.hasNextInt()) {
            positions.add(scanner.nextInt())
        }
        return positions
    }

    static def calculateFuel(List<Long> positions, long targetPosition) {
        positions.stream().mapToLong {Math.abs(targetPosition - it)}.sum()
    }

    static long calculateMoveCostPartTwo(long distance) {
        return (1..distance).sum() as long
    }

    static def calculateFuelPartTwo(List<Long> positions, long targetPosition) {
        positions.stream().mapToLong {calculateMoveCostPartTwo(Math.abs(targetPosition - it)) }.sum()
    }

    static def solvePartOne(List<Long> positions) {
        solve(new ArrayList(positions), Day7::calculateFuel)
    }

    static def solvePartTwo(List<Long> positions) {
        solve(new ArrayList(positions), Day7::calculateFuelPartTwo)
    }

    static def solve(List<Long> positions, BiFunction<List<Long>, Long, Long> fuelCalculator) {
        (positions.min()..positions.max()).stream().mapToLong() {fuelCalculator(positions, it)}.min().getAsLong()
    }

    static void main(String[] args) {
        def positions = parseInput(System.in)
        println(solvePartOne(positions))
        println(solvePartTwo(positions))
    }

}
