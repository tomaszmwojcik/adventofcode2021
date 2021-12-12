package com.github.tomaszmwojcik

class Day2 {

    def static solve(List<String> lines) {
        int position = 0
        int depth = 0
        int aim = 0
        int depthPartTwo = 0
        def p = ~/(down|up|forward) (\d+)/
        lines.forEach {
            def parsed = (it =~ p)
            if (parsed.matches()) {
                def moveSize = parsed.group(2).toInteger()
                switch (parsed.group(1)) {
                    case 'down':
                        depth += moveSize
                        aim += moveSize
                        break
                    case 'up':
                        depth -= moveSize
                        aim -= moveSize
                        break
                    case 'forward':
                        position += moveSize
                        depthPartTwo += aim * moveSize
                        break
                }
            }
        }
        [position * depth, position * depthPartTwo]
    }

    static void main(String[] args) {
        def lines = System.in.readLines()
        println(solve((lines)))
    }

}
