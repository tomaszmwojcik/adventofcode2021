package com.github.tomaszmwojcik

import spock.lang.Specification

class Day7Test extends Specification {

    def "should parse input"() {
        given:
        def input = new ByteArrayInputStream("16,1,2,0,4,2,7,1,2,14".getBytes())

        when:
        def positions = Day7.parseInput(input)

        then:
        positions.size() == 10
    }

    def "should solve part one"() {
        given:
        def input = new ByteArrayInputStream("16,1,2,0,4,2,7,1,2,14".getBytes())
        def positions = Day7.parseInput(input)

        when:
        def result = Day7.solvePartOne(positions)

        then:
        result == 37
    }

    def "should solve part two"() {
        given:
        def input = new ByteArrayInputStream("16,1,2,0,4,2,7,1,2,14".getBytes())
        def positions = Day7.parseInput(input)

        when:
        def result = Day7.solvePartTwo(positions)

        then:
        result == 168
    }

}
