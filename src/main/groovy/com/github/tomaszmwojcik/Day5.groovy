package com.github.tomaszmwojcik

import static java.lang.Math.abs
import static java.lang.Math.max
import static java.lang.Math.min

class Day5 {

    static class OceanFloor {
        List<List<Integer>> vents = [[0]]
        boolean allowDiagonal = false

        void add(int x1, int y1, int x2, int y2) {
            enlargeIfNeeded(max(x1, x2), max(y1, y2))
            if(x1 == x2) {
                for(int y = min(y1,y2); y <= max(y1,y2); y++) {
                    vents[y][x1] = vents[y][x1] + 1
                }
            }
            if(y1 == y2) {
                for(int x = min(x1,x2); x <= max(x1,x2); x++) {
                    vents[y1][x] = vents[y1][x] + 1
                }
            }
            if(allowDiagonal && !(x1 == x2 || y1 == y2)) {
                int startY = min(y1,y2)
                int stopY = max(y1, y2)
                int startX = startY == y1 ? x1 : x2
                int stopX = startY == y1 ? x2 : x1
                int diffX = (stopX - startX) / abs(stopX - startX)
                int x=startX
                for(int y=startY; y<=stopY; y++) {
                    vents[y][x] = vents[y][x] + 1
                    x += diffX
                }
            }
        }

        void enlargeIfNeeded(int x, int y) {
            int currentMaxX = vents[0].size() - 1
            int currentMaxY = vents.size() - 1
            if(currentMaxX < x) {
                vents.forEach {row -> (x - currentMaxX).times {row.add(0) } }
                currentMaxX = x
            }
            while(currentMaxY < y) {
                vents.add(new ArrayList<Integer>())
                (currentMaxX+1).times {vents.last().add(0) }
                currentMaxY++
            }
        }

        void print() {
            for(int y=0; y<vents.size(); y++) {
                for(int x=0; x<vents[y].size(); x++) {
                    print(vents[y][x])
                }
                println()
            }
        }

        int solve() {
            vents.stream().flatMap {it.stream()}.filter{it >= 2}.count()
        }
    }

    static def parseInput(InputStream input) {
        def lines = input.readLines()
        def linePattern = ~/(\d+),(\d+) -> (\d+),(\d+)/
        OceanFloor oceanFloorPartOne = new OceanFloor(allowDiagonal: false)
        OceanFloor oceanFloorPartTwo = new OceanFloor(allowDiagonal: true)
        for(String line : lines) {
            def parsed = (line =~ linePattern)
            if(parsed) {
                int x1 = parsed.group(1).toInteger()
                int y1 = parsed.group(2).toInteger()
                int x2 = parsed.group(3).toInteger()
                int y2 = parsed.group(4).toInteger()
                oceanFloorPartOne.add(x1, y1, x2, y2)
                oceanFloorPartTwo.add(x1, y1, x2, y2)
            }
        }
        return [oceanFloorPartOne, oceanFloorPartTwo]
    }

    static void main(String[] args) {
        def oceanFloor = parseInput(System.in)
        //oceanFloor.print()
        println(oceanFloor[0].solve())
        println(oceanFloor[1].solve())
    }
}
