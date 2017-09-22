package net.javacogito.burrowswhellertransform;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
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
    char[] in02 = {'u', 'u', '$', '-', 'b', 'B'};
    char[] in03 = "oo$ff-ffuuaallbB".toCharArray();
    char[] in04 = "oo$ff ffuuaallbB".toCharArray();
    char[] in05 = Main.encode("aobocodoeofo$").toCharArray();
//    char[] in06 = Main.encode("xo-yo-zo-so-po$").toCharArray();
    //char[] in06 = Main.encode("Neko n$").toCharArray();

//    Assert.assertEquals("easy-peasy$", Main.decode(in01));
//    Assert.assertEquals("Bu-bu$", Main.decode(in02));
//    Assert.assertEquals("Bufalo-bufffalo$", Main.decode(in03));
//    Assert.assertEquals("Bufalo bufffalo$", Main.decode(in04));
   // Assert.assertEquals("ko#no$", Main.decode(in05));
    System.out.println(Main.decode(in05));
  }

  @Test
  public void encodeTest(){
    //System.out.println(Main.encode("easy-peasy$"));
//    System.out.println(Main.encode("Bu-bu$"));
//    System.out.println(Main.encode("Buffalo-buffalo$"));
//    System.out.println("==============");
    Main.encode("ko#no$");

  }
}
