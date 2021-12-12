package com.github.tomaszmwojcik

import java.util.stream.Collector
import java.util.stream.Collectors

class Day8 {

    /*
      0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg


 0 - ABCEFG - 6
 1 - CF - 2
 2 - ACDEG - 5
 3 - ACDFG - 5
 4 - BCDF - 4
 5 - ABDFG - 5
 6 - ABDEFG - 6
 7 - ACF - 3
 8 - ABCDEFG - 7
 9 - ABCDFG - 6

 idea - create map<char, set<char>> fill it with all combinations, and remove them based on digit length
     */

    static List<String> digits = ['abcefg', 'cf', 'acdeg', 'acdfg', 'bcdf', 'abdfg', 'abdefg', 'acf', 'abcdefg', 'abcdfg']
    static Map<Integer, Long> lengthCounts = digits.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()))

    static class Entry {
        List<String> signalPatterns
        List<String> output
    }

    static def decodeEntry(Entry entry) {
        Map<String, Set<String>> entryCharToDigitChar = new HashMap<>()
        ('a'..'g').each {
            entryCharToDigitChar[it] = new HashSet<String>(('a'..'g').toList())
        }
        String twoLetterPattern = ""
        entry.signalPatterns.each {signalPattern ->
            digits.each {digit ->
                if (digit.size() == signalPattern.size() && lengthCounts[digit.size()] == 1) {
                    // we have identified matching digits
                    signalPattern.each {
                        entryCharToDigitChar[it].retainAll(digit.toList())
                    }
                    if(digits.size() == 2) {
                        twoLetterPattern = signalPattern
                    }
                }
            }
        }
        entryCharToDigitChar.values().each {
            if(it.size() != 2) {
                it.removeAll('cf'.toList())
            }
        }
        return entryCharToDigitChar
    }

    static def parseInput(InputStream input) {
        List<Entry> entries = []
        for(String line : input.readLines()) {
            Entry entry = new Entry(signalPatterns: [], output: [])
            def (patterns, outputs) = line.split("[|]")
            Scanner scanner = new Scanner(patterns)
            while(scanner.hasNext()) {
                entry.signalPatterns.add(scanner.next())
            }
            Scanner scannerOutputs = new Scanner(outputs)
            while(scannerOutputs.hasNext()) {
                entry.output.add(scannerOutputs.next())
            }
            entries.add(entry)
        }
        return entries
    }

    static def solvePartOne(List<Entry> entries) {
        Set<Integer> knownDigits = Set.of(2,3,4,7)
        entries.stream()
                .flatMap {it.output.stream()}
                .filter {knownDigits.contains(it.size()) }
                .count()
    }

    static def solvePartTwo(List<Entry> entries) {
        decodeEntry(entries[0])
    }

    static void main(String[] args) {
        def input = parseInput(System.in)
        println(solvePartOne(input))
    }
}
