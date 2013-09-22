package com.javacogito.portfolio.deepforest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is written by Igor Kulikov.
 */
public class ProfilerAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfilerAware.class);

    public static void execute(Runnable runnable) {
        try {
            waitSomeTime("!!! time to connect !!!");
            Thread t = new Thread(runnable);
            t.start();
            t.join();
            waitSomeTime("!!! time to dissconnect !!!");
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private static void waitSomeTime(String message) {
        try {
            LOGGER.warn(message);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}