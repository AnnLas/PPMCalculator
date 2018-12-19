package com.example.anna.ppmcalculator;

public class PPM {
    public double pMMBenedictHarrisWay(String gender, double weight, double height, int age) {
        double result;
        if (gender.equals("Man")) {
            result = 66.5 + (13.75 * weight) + (5.003 * height) - ((double) age * 6.775);
        } else
            result = 655.1 + (9.563 * weight) + (1.85 * height) - ((double) age * 4.676);
        return result;

    }

    public double pMMMiffilinWay(String gender, double weight, double height, int age) {
        double result;
        if (gender.equals("Man")) {
            result = (10 * weight) + (6.25 * height) - ((double) age * 5) + 5;
        } else
            result = (10 * weight) + (6.25 * height) - ((double) age * 5) - 161;
        return result;

    }
}
