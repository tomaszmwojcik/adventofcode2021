package com.github.tomaszmwojcik

import java.util.stream.Collectors
import java.util.stream.IntStream

class Day3 {
    /*
     input: list of binary numbers
     use each binary number to generate two new binary numbers
     gamma rate = construct it so that each bit is the most common bit in given position of all numbers in the input,
        then convert it to decimal
     epsilon rate = binary reverse of gamma rate - or take the least common bit for each position
     power consumption = gamma rate * epsilon rate
     */

    static int solvePartOne(List<String> input) {
        int wordLength = input[0].size()
        List<Integer> zeros = IntStream.generate(it -> 0).limit(wordLength).collect()
        List<Integer> sums = new ArrayList<>(zeros)
        for(String word : input) {
            for(int i=0; i<wordLength; i++) {
                sums[i] = sums[i] + word[i].toInteger()
            }
        }
        StringBuilder gammaBinary = new StringBuilder()
        StringBuilder epsilonBinary = new StringBuilder()
        for(int i=0; i<wordLength; i++) {
            if(sums[i] > input.size()/2) {
                gammaBinary.append('1')
                epsilonBinary.append('0')
            } else {
                gammaBinary.append('0')
                epsilonBinary.append('1')
            }
        }
        int gamma = Integer.parseInt(gammaBinary.toString(), 2)
        int epsilon = Integer.parseInt(epsilonBinary.toString(), 2)
        return gamma * epsilon
    }

    /*
     * find oxygen, co2 rating
     * rules
     * consider just first bit for start
     * keep only number selected by the bit criteria
     * if you have only one number left this is the correct one
     * otherwise repeat, considering the next bit to the right
     * oxygen - most common value in that position, if count(0) == count(1) then 1
     * co2 - least common value in that position, if count(0) == count(1) then 0
     */

    static int solvePartTwo(List<String> input) {
        int oxygen = solvePartTwoOxygen(new ArrayList<String>(input), 0)
        int co2 = solvePartTwoCo2(new ArrayList<String>(input), 0)
        return oxygen * co2
    }

    static int solvePartTwoCo2(List<String> co2Candidates, int position) {
        int co2PositionSum = 0
        for(String word : co2Candidates) {
            co2PositionSum += word[position].toInteger()
        }
        String co2Bit = '1'
        if(co2PositionSum >= co2Candidates.size()/2) {
            co2Bit = '0'
        }
        co2Candidates.removeAll {it[position] != co2Bit}
        if(co2Candidates.size() == 1) {
            return Integer.parseInt(co2Candidates.get(0), 2)
        } else {
            return solvePartTwoCo2(co2Candidates, position+1)
        }
    }

    static int solvePartTwoOxygen(List<String> oxygenCandidates, int position) {
        int oxygenPositionSum = 0
        for(String word : oxygenCandidates) {
            oxygenPositionSum += word[position].toInteger()
        }

        String oxBit = '0'
        if(oxygenPositionSum >= oxygenCandidates.size()/2) {
            oxBit = '1'
        }
        oxygenCandidates.removeAll {it[position] != oxBit}
        if(oxygenCandidates.size() == 1) {
            return Integer.parseInt(oxygenCandidates.get(0), 2)
        } else {
            return solvePartTwoOxygen(oxygenCandidates, position+1)
        }
    }

    static void main(String[] args) {
        def lines = System.in.readLines()
        println(solvePartOne(lines))
        println(solvePartTwo(lines))
    }

}
