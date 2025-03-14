package com.github.dmhenry.bits;

import java.util.function.Function;

public final class LookupTableBitCounter implements Function<BinaryInteger, Integer> {

    int[] counts = new int[256];

    public LookupTableBitCounter() {
        for (int i = 0; i < counts.length; i++) {
            counts[i] = (i & 1) + counts[i / 2];
        }
    }

    @Override
    public Integer apply(BinaryInteger binaryInteger) {
        int n = binaryInteger.intValue();
        return (counts[n & 0xff]
                + counts[(n >> 8) & 0xff]
                + counts[(n >> 16) & 0xff]
                + counts[(n >> 24) & 0xff]
        );
    }

}
