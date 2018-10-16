package net.javacogito.stringpermutations;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.net.URL;


public class MainTest {
  @org.junit.Test
  public void mainTest() throws IOException {
    URL url = Thread.currentThread().getContextClassLoader().getResource("data001.in");
    String[] input = {url.getPath()};
    Main.main(input);
  }
}
