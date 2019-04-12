package com.hzl.base.util;

import java.util.Random;

public class RandomUtil {
    static Random random = new Random();

    public static double randDouble() {
        return random.nextDouble();
    }
}
