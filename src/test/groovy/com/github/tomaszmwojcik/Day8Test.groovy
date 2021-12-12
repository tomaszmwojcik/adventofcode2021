package com.github.tomaszmwojcik

import spock.lang.Specification

class Day8Test extends Specification {

    def "should parse input"() {
        given:
        def input = Day8Test.getClassLoader().getResourceAsStream("day8.txt")

        when:
        def entries = Day8.parseInput(input)

        then:
        entries.size() == 10
        entries[0].signalPatterns.size() == 10
        entries[0].output.size() == 4
    }

    def "should solve part one"() {
        given:
        def input = Day8Test.getClassLoader().getResourceAsStream("day8.txt")
        def entries = Day8.parseInput(input)

        when:
        def result = Day8.solvePartOne(entries)

        then:
        result == 26
    }

    def "should solve part two"() {
        given:
        def input = Day8Test.getClassLoader().getResourceAsStream("day8.txt")
        def entries = Day8.parseInput(input)

        when:
        def result =  Day8.solvePartTwo(entries)

        then:
        result == 61229
    }

}
