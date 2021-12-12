package com.github.tomaszmwojcik

import spock.lang.Specification

class Day6Test extends Specification {

    def "should parse input"() {
        given:
        def input = new ByteArrayInputStream("3,4,3,1,2".getBytes())

        when:
        def ages = Day6.parseInput(input)

        then:
        ages.size() == 5
    }

    def "should solve part one"() {
        given:
        def input = new ByteArrayInputStream("3,4,3,1,2".getBytes())

        when:
        def fishCount = Day6.solvePartOne(input)

        then:
        fishCount == 5934
    }

    def "should solve part two"() {
        given:
        def input = new ByteArrayInputStream("3,4,3,1,2".getBytes())

        when:
        def fishCount = Day6.solvePartTwo(input)

        then:
        fishCount == 26984457539
    }

}
