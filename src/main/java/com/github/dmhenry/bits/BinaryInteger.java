package com.github.dmhenry.bits;

import java.util.function.Function;

public final class BinaryInteger {

    private final int n;
    private final Function<BinaryInteger, Integer> bitCountingAlgorithm;

    public BinaryInteger(int n) {
        this(n, new KernighanBitCounter());
    }

    public BinaryInteger(int n, Function<BinaryInteger, Integer> bitCountingAlgorithm) {
        this.n = n;
        this.bitCountingAlgorithm = bitCountingAlgorithm;
    }

    public boolean isBitSet(int k) {
        return ((1 << k) & n) != 0;
    }

    public int bitCount() {
        return bitCountingAlgorithm.apply(this);
    }

    public int intValue() {
        return n;
    }

}
