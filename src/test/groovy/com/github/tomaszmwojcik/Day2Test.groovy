package com.github.tomaszmwojcik

import spock.lang.Specification

class Day2Test extends Specification {

    def "should solve day2 puzzle"() {
        given:
        def input = """forward 5
down 5
forward 8
up 3
down 8
forward 2"""

        when:
        def solution = Day2.solve(input.split("\n").toList())

        then:
        solution == [150, 900]
    }

}
