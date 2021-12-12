package com.github.tomaszmwojcik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    /**
     *
     * @param depths
     * @return the number of elements that are higher than the previous element
     */
    public static int solvePartOne(List<Integer> depths) {
        int lastDepth = depths.get(0);
        int increasesCount = 0;
        for(int i=1; i<depths.size(); i++) {
            if(depths.get(i) > lastDepth) {
                increasesCount++;
            }
            lastDepth = depths.get(i);
        }
        return increasesCount;
    }

    /**
     *
     * @param depths
     * @return like above but using three-number rolling average
     */
    public static int solvePartTwo(List<Integer> depths) {
        int lastDepth = depths.get(0)+depths.get(1)+depths.get(2);
        int increasesCount = 0;
        for(int i=2; i<depths.size()-1; i++) {
            int depthAvg = depths.get(i-1) + depths.get(i) + depths.get(i+1);
            if(depthAvg > lastDepth) {
                increasesCount++;
            }
            lastDepth = depthAvg;
        }
        return increasesCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> depths = new ArrayList<>();
        while(scanner.hasNextInt()) {
            depths.add(scanner.nextInt());
        }
        System.out.println(solvePartOne(depths));
        System.out.println(solvePartTwo(depths));
    }
}
