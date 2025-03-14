package com.github.dmhenry.bits;

import java.util.function.Function;

public final class KernighanBitCounter implements Function<BinaryInteger, Integer> {

    @Override
    public Integer apply(BinaryInteger value) {
        int count = 0, n = value.intValue();
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

}
