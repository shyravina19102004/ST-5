package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class SqrtTest {
    private static final double DELTA = 0.0001;

    @Test
    public void testAverageWithNegativeNumbers() {
        Sqrt sqrt = new Sqrt(4.0);
        assertEquals(-1.0, sqrt.average(-3.0, 1.0), DELTA);
    }

    @Test
    public void testAverageWithZero() {
        Sqrt sqrt = new Sqrt(4.0);
        assertEquals(2.5, sqrt.average(5.0, 0.0), DELTA);
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 4.0, true",
            "1.9999999, 4.0, true",
            "1.9, 4.0, false",
            "0.0, 0.0, true"
    })
    public void testGoodWithVariousValues(double guess, double x, boolean expected) {
        Sqrt sqrt = new Sqrt(x, 1e-6);
        assertEquals(expected, sqrt.good(guess, x));
    }

    @Test
    public void testImproveWithLargeNumbers() {
        Sqrt sqrt = new Sqrt(1e10);
        assertEquals(1e5, sqrt.improve(1e5, 1e10), 0.1);
    }

    @Test
    public void testImproveWithSmallNumbers() {
        Sqrt sqrt = new Sqrt(0.25);
        assertEquals(0.5, sqrt.improve(0.5, 0.25), DELTA);
    }

    @Test
    public void testIterWithBadInitialGuess() {
        Sqrt sqrt = new Sqrt(25.0);
        assertEquals(5.0, sqrt.iter(100.0, 25.0), DELTA);
    }

    @Test
    public void testCalcForOne() {
        Sqrt sqrt = new Sqrt(1.0);
        assertEquals(1.0, sqrt.calc(), DELTA);
    }

    @Test
    public void testCalcForZero() {
        Sqrt sqrt = new Sqrt(0.0);
        assertEquals(0.0, sqrt.calc(), DELTA);
    }

    @Test
    public void testCalcForDecimal() {
        Sqrt sqrt = new Sqrt(2.25);
        assertEquals(1.5, sqrt.calc(), DELTA);
    }

    @ParameterizedTest
    @ValueSource(doubles = {4.0, 9.0, 16.0, 25.0, 36.0})
    public void testCalcForPerfectSquares(double value) {
        Sqrt sqrt = new Sqrt(value);
        assertEquals(Math.sqrt(value), sqrt.calc(), DELTA);
    }

    @Test
    public void testCalcForNonPerfectSquares() {
        Sqrt sqrt = new Sqrt(3.0);
        assertEquals(1.73205, sqrt.calc(), DELTA);
    }

    @Test
    public void testPrecision() {
        Sqrt sqrt = new Sqrt(2.0, 1e-12);
        double result = sqrt.calc();
        assertEquals(Math.sqrt(2.0), result, 1e-8);
    }

    @Test
    public void testVerySmallNumber() {
        Sqrt sqrt = new Sqrt(1e-10, 1e-15);
        assertEquals(1e-5, sqrt.calc(), 1e-8);
    }

    @Test
    public void testVeryLargeNumber() {
        Sqrt sqrt = new Sqrt(1e10);
        assertEquals(1e5, sqrt.calc(), 1);
    }

    @Test
    public void testNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> new Sqrt(-4.0));
    }

    @Test
    public void testImproveWithNegativeGuess() {
        Sqrt sqrt = new Sqrt(4.0);
        assertEquals(-2.05, sqrt.improve(-2.0, 4.2), DELTA);
    }

    @Test
    public void testMultipleCalcCalls() {
        Sqrt sqrt = new Sqrt(4.0);
        assertEquals(2.0, sqrt.calc(), DELTA);
        assertEquals(2.0, sqrt.calc(), DELTA);
    }
}
