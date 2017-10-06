package net.javacogito.lowestcommonancestor;

import java.io.IOException;
import java.net.URL;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import  static net.javacogito.lowestcommonancestor.Main.Node;
import  static net.javacogito.lowestcommonancestor.Main.ROOT;
import static net.javacogito.lowestcommonancestor.Main.getNodeById;

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
  public void getNodeByIdTest(){
    Assert.assertEquals("20", Main.getNodeById("20").toString());
    Assert.assertEquals("29", Main.getNodeById("29").toString());
    Assert.assertEquals("3", Main.getNodeById("3").toString());
  }


  @Test
  public void pathToRootTest(){
    Node node20 = Main.getNodeById("20");
    List<Node> pathToRoot20 = new ArrayList<>();
    pathToRoot20.add(ROOT);
    pathToRoot20.add(getNodeById("8"));
    pathToRoot20.add(node20);
    Assert.assertEquals(pathToRoot20, node20.pathToRoot());

    Node node29 = Main.getNodeById("29");
    List<Node> pathToRoot29 = new ArrayList<>();
    pathToRoot29.add(ROOT);
    pathToRoot29.add(getNodeById("8"));
    pathToRoot29.add(getNodeById("20"));
    pathToRoot29.add(node29);
    Assert.assertEquals(pathToRoot29, node29.pathToRoot());

  }

  @Test
  public void lowestCommonAncestorTest(){
//    Assert.assertEquals("30", Main.lowestCommonAncestor(Main.getNodeById("8"), Main.getNodeById("20")).toString());
//    Assert.assertEquals("30", Main.lowestCommonAncestor(Main.getNodeById("8"), Main.getNodeById("52")).toString());
//    Assert.assertEquals("8", Main.lowestCommonAncestor(Main.getNodeById("3"), Main.getNodeById("29")).toString());
//    Assert.assertEquals("30", Main.lowestCommonAncestor(Main.getNodeById("29"), Main.getNodeById("8")).toString());
//    Assert.assertEquals("20", Main.lowestCommonAncestor(Main.getNodeById("10"), Main.getNodeById("29")).toString());
    Assert.assertEquals("30", Main.lowestCommonAncestor(Main.getNodeById("8"), Main.getNodeById("30")).toString());
    System.out.println();
  }
}