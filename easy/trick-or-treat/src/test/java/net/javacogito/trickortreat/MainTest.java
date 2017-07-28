package net.javacogito.trickortreat;

import java.io.IOException;
import java.net.URL;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import org.junit.Test;

public class MainTest {

  @Test
  public void mainTest() throws IOException, ParseException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }
}

