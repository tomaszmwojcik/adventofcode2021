package com.github.tomaszmwojcik

class Day4 {

    /*
    Input:

     */

    static class BingoNumber {
        int number
        boolean checked = false
    }

    static class Board {
        List<List<BingoNumber>> content
        boolean hasWon

        void markNumber(int number) {
            content.stream()
                    .flatMap(it -> it.stream())
                    .filter(it -> it.number == number)
                    .findFirst()
                    .ifPresent(it -> it.checked = true)
        }

        boolean isWon() {
            for(List<BingoNumber> row : content) {
                if(row.stream().filter{it.checked}.count() == row.size()) {
                    return true
                }
            }
            for(int i=0; i<content.get(0).size(); i++) {
                if(content.stream().filter(row -> row[i].checked).count() == content.size()) {
                    return true
                }
            }
        }

        int getSumOfAllUnmarked() {
            content.stream()
                    .flatMap(it -> it.stream())
                    .filter(it -> !it.checked)
                    .mapToInt(it -> it.number)
                    .sum()
        }

        void resetChecked() {
            content.stream()
                    .flatMap(it -> it.stream())
                    .forEach(it -> it.checked = false)
        }
    }

    static def parseInput(InputStream input) {
        def scanner = new Scanner(input)
        List<Integer> numbers = []
        scanner.useDelimiter("[,\n]")
        while(scanner.hasNextInt()) {
            numbers.add(scanner.nextInt())
        }

        scanner.reset()
        scanner.skip(/\s*/)
        int positionInBoard = 0
        List<Board> boards = []
        while(scanner.hasNextInt()) {
            if(positionInBoard % 25 == 0) {
                boards.add(new Board(content: [[]]))
            }
            boards.last().content.last().add(new BingoNumber(number: scanner.nextInt(), checked: false))
            positionInBoard++
            if(positionInBoard % 5 == 0 && positionInBoard % 25 != 0) {
                boards.last().content.add([])
            }

        }
        return [numbers, boards]
    }

    static int solvePartOne(List<Integer> numbers, List<Board> boards) {
        for(int number : numbers) {
            println("Checking ${number}")
            for(Board board : boards) {
                board.markNumber(number)
                if(board.isWon()) {
                    println("${number} ${board.getSumOfAllUnmarked()}")
                    return board.getSumOfAllUnmarked() * number
                }
            }
        }
    }

    static int solvePartTwo(List<Integer> numbers, List<Board> boards) {
        int lastWon = -1
        for(int number : numbers) {
            println("Checking ${number}")
            for(Board board : boards) {
                if(!board.hasWon) {
                    board.markNumber(number)
                    if(board.isWon()) {
                        lastWon = board.getSumOfAllUnmarked() * number
                        board.hasWon = true
                        println("won")
                    }
                }
            }
        }
        return lastWon
    }

    static void main(String[] args) {
        def (numbers, boards) = parseInput(System.in)
        println(solvePartOne(numbers, boards))
        boards.forEach { it.resetChecked()}
        println(solvePartTwo(numbers, boards))
    }

}
