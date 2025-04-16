package com.mycompany.app;

public class Sqrt {
    private static final double DEFAULT_DELTA = 1e-10;
    private final double delta;
    private final double arg;

    public Sqrt(double arg) {
        this(arg, DEFAULT_DELTA);
    }

    public Sqrt(double arg, double delta) {
        if (arg < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        this.arg = arg;
        this.delta = delta;
    }

    public double average(double x, double y) {
        return (x + y) / 2.0;
    }

    public boolean good(double guess, double x) {
        return Math.abs(guess * guess - x) < delta;
    }

    public double improve(double guess, double x) {
        if (guess == 0) return 0;
        return average(guess, x / guess);
    }

    public double iter(double guess, double x) {
        double improved = improve(guess, x);
        if (good(improved, x)) {
            return improved;
        }
        return iter(improved, x);
    }

    public double calc() {
        if (arg == 0) return 0;
        if (arg == 1) return 1;
        return iter(arg/2, arg);
    }
}