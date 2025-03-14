package com.github.dmhenry.bits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BinaryIntegerTest {

    @ParameterizedTest
    @MethodSource("isBitSetProvider")
    void testIsBitSet(int n, int k, boolean expected) {
        BinaryInteger value = new BinaryInteger(n);
        assertEquals(expected, value.isBitSet(k));
    }

    @ParameterizedTest
    @MethodSource("bitCountProvider")
    void testBitCount(int n, int expected, Function<BinaryInteger, Integer> algorithm) {
        BinaryInteger value = new BinaryInteger(n, algorithm);
        assertEquals(expected, value.bitCount());
    }

    private static Stream<Arguments> isBitSetProvider() {
        return Stream.of(
                arguments(0, 0, false),
                arguments(0, 31, false),
                arguments(1, 0, true),
                arguments(1, 31, false),
                arguments(-1, 0, true),
                arguments(-1, 31, true),
                arguments(Integer.MIN_VALUE, 0, false),
                arguments(Integer.MIN_VALUE, 30, false),
                arguments(Integer.MIN_VALUE, 31, true),
                arguments(Integer.MAX_VALUE, 0, true),
                arguments(Integer.MAX_VALUE, 30, true),
                arguments(Integer.MAX_VALUE, 31, false)
        );
    }

    private static Stream<Arguments> bitCountProvider() {
        var kernighanBitCounter = new KernighanBitCounter();
        var lookupTableBitCounter = new LookupTableBitCounter();
        return Stream.of(
                arguments(0, 0, kernighanBitCounter),
                arguments(0, 0, lookupTableBitCounter),
                arguments(2, 1, kernighanBitCounter),
                arguments(2, 1, lookupTableBitCounter),
                arguments(-15, 29, kernighanBitCounter),
                arguments(-15, 29, lookupTableBitCounter),
                arguments(0b10101010101011111100001010110110, 18, kernighanBitCounter),
                arguments(0b10101010101011111100001010110110, 18, lookupTableBitCounter),
                arguments(0b00001010101011101100001010110111, 16, kernighanBitCounter),
                arguments(0b00001010101011101100001010110111, 16, lookupTableBitCounter),
                arguments(Integer.MIN_VALUE, 1, kernighanBitCounter),
                arguments(Integer.MIN_VALUE, 1, lookupTableBitCounter),
                arguments(Integer.MAX_VALUE, 31, kernighanBitCounter),
                arguments(Integer.MAX_VALUE, 31, lookupTableBitCounter)
        );
    }

}
