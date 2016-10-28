package net.javacogito.multiplicationtables;


import org.junit.Test;

public class MainTest {

    @Test
    public void mainTest() {
        long startTime = System.currentTimeMillis();
        Main.main(new String[0]);
        long endTime = System.currentTimeMillis();
        long intervalMilliseconds = endTime - startTime;
        long intervalSeconds = intervalMilliseconds / 1000;
        System.out.println("duration = " + intervalSeconds);
    }
}