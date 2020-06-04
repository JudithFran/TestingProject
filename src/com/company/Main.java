package com.company;

import java.io.*;

/**
 *
 * @author Judith
 */

public class Main {

    public static void main(String[] args) {
        // write your code here
        try {
            System.out.println("Hello Judith");

            long startTime = System.currentTimeMillis();

            Deckard dk = new Deckard();
            dk.testingDeckardInputFile();

            ConQat cq = new ConQat();
            //cq.testingConQatInputFile();

            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            long sTime = (long) (elapsedTime*0.001);
            long mTime = sTime/60;
            sTime = sTime%60;

            System.out.println("Total execution time for processing single ConQat clone file = " + mTime + " minutes " + sTime + " seconds");

        } catch (Exception e) {
            System.out.println("Error in main() method = " + e);
            e.printStackTrace();
        }
    }
}