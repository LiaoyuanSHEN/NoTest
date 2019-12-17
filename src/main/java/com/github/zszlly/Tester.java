package com.github.zszlly;

import com.github.zszlly.annotation.NoTest;

public class Tester {

    int a;

    @NoTest
    public int add(int a, int b) {
        int value = a + b;
        System.out.println(a + " + " + b + " = " + value);
        return value;
    }

    @NoTest
    public int addB(int a, GetB getB) {
        int b = getB.getBFn();
        int value = a + b;
        System.out.println(a + " + " + b + " = " + value);
        return value;
    }

    @NoTest
    public int addArray(Integer[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        System.out.println("sum = " + sum);
        return sum;
    }

    @NoTest
    public int addAAndB(GetB getB) {
        int b = getB.getBFn();
        int value = a + b;
        System.out.println(a + " + " + b + " = " + value);
        return value;
    }

    public interface GetB {
        int getBFn();
    }
}
