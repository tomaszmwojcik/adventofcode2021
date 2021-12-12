package com.github.tomaszmwojcik

import spock.lang.Specification

class Day3Test extends Specification {
    def input = """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""

    def "should solve day 3 puzzles"() {
        when:
        def result = Day3.solvePartOne(input.split('\n').toList())

        then:
        result == 198
    }

    def "should find oxygen level"() {
        when:
        def oxygen = Day3.solvePartTwoOxygen(input.split('\n').toList(), 0)

        then:
        oxygen == 23
    }

    def "should find co2 level"() {
        when:
        def co2 = Day3.solvePartTwoCo2(input.split('\n').toList(), 0)

        then:
        co2 == 10
    }

}
