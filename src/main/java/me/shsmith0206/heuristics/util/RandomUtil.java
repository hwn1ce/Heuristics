package me.shsmith0206.heuristics.util;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {
    public static int threadLocalInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }
}
