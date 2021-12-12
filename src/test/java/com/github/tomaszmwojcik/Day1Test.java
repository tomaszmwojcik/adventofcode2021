package com.github.tomaszmwojcik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day1Test {

    List<Integer> testInput = List.of(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263);

    @Test
    public void shouldSolvePartOne() {
        //when
        int result = Day1.solvePartOne(testInput);

        //then
        Assertions.assertEquals(7, result);
    }

    @Test
    public void shouldSolvePartTwo() {
        //when
        int result = Day1.solvePartTwo(testInput);

        //then
        Assertions.assertEquals(5, result);
    }

}
