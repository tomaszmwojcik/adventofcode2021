package com.github.tomaszmwojcik

class Day6 {

    private static final int OLD_FISH_COOLDOWN = 6
    private static final int NEW_FISH_COOLDOWN = 8

    static def parseInput(InputStream input) {
        Scanner scanner = new Scanner(input)
        scanner.useDelimiter(/[,\n]/)
        List<Integer> ages = []
        while(scanner.hasNextInt()) {
            ages.add(scanner.nextInt())
        }
        return ages
    }

    static def solvePartOne(InputStream input) {
        def ages = parseInput(input)
        return simulate(ages, 80)
    }

    static def simulate(List<Integer> ages, int days) {
        //println("Initial: ${ages}")
        for(int day=1; day <= days; day++) {
            simulateOneDay(ages)
            //println("${day}: ${ages.size()}")
        }
        return ages.size()
    }

    static def simulateOneDay(List<Integer> ages) {
        int fishToAdd = 0
        for(int i=0; i<ages.size(); i++) {
            if(ages[i] == 0) {
                ages[i] = OLD_FISH_COOLDOWN
                fishToAdd++
            } else {
                ages[i] = ages[i] - 1
            }
        }
        fishToAdd.times {ages.add(NEW_FISH_COOLDOWN)}
    }

    static def solvePartTwo(InputStream input) {
        def ages = parseInput(input)
        return simulateSmart(ages, 256)
    }

    static def solve(InputStream input) {
        def ages = parseInput(input)
        println(simulate(new ArrayList<>(ages), 80))
        println(simulateSmart(new ArrayList<>(ages), 256))
    }

    static BigInteger simulateSmart(List<Integer> ages, int days) {
        List<BigInteger> cooldownToCount = [0G,0G,0G,0G,0G,0G,0G,0G,0G]
        ages.forEach {
            cooldownToCount[it] = cooldownToCount[it] + 1
        }
        for(int day=1; day <= days; day++) {
            simulateOneDaySmart(cooldownToCount)
        }
        return cooldownToCount.sum() as BigInteger
    }

    static def simulateOneDaySmart(List<BigInteger> cooldownToCount) {
        def fishToAdd = cooldownToCount.remove(0)
        cooldownToCount[OLD_FISH_COOLDOWN] = cooldownToCount[OLD_FISH_COOLDOWN] + fishToAdd
        cooldownToCount.add(fishToAdd)
    }

    static void main(String[] args) {
        println(solve(System.in))
    }

}
