package net.javacogito.burrowswhellertransform;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

public class MainTest {

  @Test
  public void mainTest() throws IOException {
    long startTime = System.currentTimeMillis();
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }

  @Test
  public void recreateTest(){
    char[] in01 = {'y','y','e','e','p','$','-','a','a','s','s'};
    char[] in02 = Main.encode("Bu bu$").toCharArray();
    System.out.println(Main.decode(in01));
    System.out.println(Main.decode(in02));
  }

  @Test
  public void encodeTest(){
    System.out.println(Main.encode("easy-peasy$"));
    System.out.println(Main.encode("Buffalo buffalo$"));
  }
}
