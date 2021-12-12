package com.github.tomaszmwojcik

import spock.lang.Specification

class Day4Test extends Specification {

    def "should parse input"() {
        given:
        def stream = Day4Test.getClassLoader().getResourceAsStream("day4.txt")

        when:
        def input = Day4.parseInput(stream)

        then:
        List<Integer> numbers = input.get(0)
        numbers.get(0) == 7
        numbers.last() == 1

        List<Day4.Board> boards = input.get(1)
        boards.size() == 3
    }

    def "should solve part one"() {
        given:
        def stream = Day4Test.getClassLoader().getResourceAsStream("day4.txt")
        def (numbers, boards) =  Day4.parseInput(stream)

        when:
        def answer = Day4.solvePartOne(numbers, boards)

        then:
        answer == 4512
    }

    def "should solve part two"() {
        given:
        def stream = Day4Test.getClassLoader().getResourceAsStream("day4.txt")
        def (numbers, boards) =  Day4.parseInput(stream)

        when:
        def answer = Day4.solvePartTwo(numbers, boards)

        then:
        answer == 1924
    }

}
