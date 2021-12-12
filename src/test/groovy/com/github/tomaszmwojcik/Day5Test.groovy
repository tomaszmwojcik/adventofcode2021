package com.github.tomaszmwojcik

import spock.lang.Specification

class Day5Test extends Specification {

    def "should solve part one"() {
        given:
        def stream = Day5Test.getClassLoader().getResourceAsStream("day5.txt")
        def input = Day5.parseInput(stream)

        when:
        def result = input[0].solve()

        then:
        result == 5
    }

    def "should solve part two"() {
        given:
        def stream = Day5Test.getClassLoader().getResourceAsStream("day5.txt")
        def input = Day5.parseInput(stream)

        when:
        def result = input[1].solve()

        then:
        result == 12
    }

}
