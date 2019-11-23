package com.hzl.base.util;

import java.util.Random;

public class RandomUtil {
    static Random random = new Random();

    public static double randDouble() {
        return random.nextDouble();
    }
    public static int randInt(int max) {
        return random.nextInt(max);
    }
}
