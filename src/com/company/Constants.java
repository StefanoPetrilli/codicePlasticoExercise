package com.company;

public class Constants {

    public static final int DIMENSION = 100;

    /**
     * The % operator does not work like the algebraic module
     * when it comes to negative numbers.
     * To implement wrapping we need that
     * -1 mod 100 = 99 (algebraic module)
     * but
     * -1 % 100 == -1 (programming module)
     * so I implemented the algebraic module myself.
     *
     * @param i we want to knoe the module of i
     * @return the algebraic module of i
     */
    public static int moduleDimension(int i) {
        if (i >= 0) return i % DIMENSION;
        return DIMENSION - (i * -1);
    }
}
