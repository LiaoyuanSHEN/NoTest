package com.github.zszlly;

import com.github.zszlly.mark.NoTestMark;

public class DummyMain implements NoTestMark {

    public static void main(String[] args) throws Throwable {
        System.out.println("Press any key to start demo program");
        System.in.read();
        Tester tester = new Tester();
        tester.add(1, 2);
        tester.add(2, 3);
        tester.add(3, 4);
        tester.add(4, 5);
        tester.addB(1, () -> 2);
        tester.addB(2, () -> 3);
        tester.addArray(new Integer[]{1, 2, 3, 4, 5});
        tester.addArray(new Integer[]{2, 3, 4, 5, 6});
        tester.a = 1;
        tester.addAAndB(() -> 2);
        tester.a = 2;
        tester.addAAndB(() -> 3);
        System.out.println("Done.");
    }

}
