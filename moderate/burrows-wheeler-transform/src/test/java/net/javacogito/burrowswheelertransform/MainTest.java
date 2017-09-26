package net.javacogito.burrowswheelertransform;

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
    char[] in04 = "oo$ff ffuuaallBb".toCharArray();
    char[] in05 = Main.encode("1o2o3o4o5o6o$").toCharArray();
    char[] in06 = Main.encode("xo-yo-zo-so-po$").toCharArray();

    Assert.assertEquals("easy-peasy$", Main.decode(in01));
    Assert.assertEquals("Bu-bu$", Main.decode(in02));
    Assert.assertEquals("Buffalo-buffalo$", Main.decode(in03));
    Assert.assertEquals("Buffalo buffalo$", Main.decode(in04));
    Assert.assertEquals("1o2o3o4o5o6o$", Main.decode(in05));
    Assert.assertEquals("xo-yo-zo-so-po$", Main.decode(in06));
    System.out.println(Main.decode(in05));
  }

  @Test
  public void recreateLongevityTest(){
    long startTime = System.currentTimeMillis();
    String data = generate(10);
    System.out.println("length = " + data.length());
    char[] in = Main.encode(data).toCharArray();
    Main.decode(in);
    long endTime = System.currentTimeMillis();
    long intervalMilliseconds = endTime - startTime;
    long intervalSeconds = intervalMilliseconds / 1000;
    System.out.println("duration = " + intervalSeconds);
  }

  @Test
  public void encodeTest(){
    Main.encode("Buffalo buffalo$");
    Main.encode("And if he disobeys the law, and pollutes the city and the temples contrary to law, and one of the magistrates sees him and does not indict him, when he gives in his account this omission shall be a most serious charge. The magistrates who keep the city should be wakeful, and the master of the household should be up early and before all his servants and the mistress, too, should awaken her handmaidens, and not be awakened by them. BOOK X.$");
    Main.encode("As they rounded a bend in the path that ran beside the river, Lara recognized the silhouette of a fig tree atop a nearby hill. The weather was hot and the days were long. The fig tree was in full leaf, but not yet bearing fruit.\n" + "Soon Lara spotted other landmarks—an outcropping of limestone beside the path that had a silhouette like a man’s face, a marshy spot beside the river where the waterfowl were easily startled, a tall tree that looked like a man with his arms upraised. They were drawing near to the place where there was an island in the river. The island was a good spot to make camp. They would sleep on the island tonight.\n" + "Lara had been back and forth along the river path many times in her short life. Her people had not created the path—it had always been there, like the river—but their deerskin-shod feet and the wooden wheels of their handcarts kept the path well worn. Lara’s people were salt traders, and their livelihood took them on a continual journey.\n" + "At the mouth of the river, the little group of half a dozen intermingled families gathered salt from the great salt beds beside the sea. They groomed and sifted the salt and loaded it into handcarts. When the carts were full, most of the group would stay behind, taking shelter amid rocks and simple lean-tos, while a band of fifteen or so of the heartier members set out on the path that ran alongside the river.\n" + "With their precious cargo of salt, the travelers crossed the coastal lowlands and traveled toward the mountains. But Lara’s people never reached the mountaintops; they traveled only as far as the foothills. Many people lived in the forests and grassy meadows of the foothills, gathered in small villages. In return for salt, these people would give Lara’s people dried meat, animal skins, cloth spun from wool, clay pots, needles and scraping tools carved from bone, and little toys made of wood.$");
  }


  private static String generate(int times){
    String template = "0123456789";
    StringBuilder sb = new StringBuilder();
    for(int i = 1; i <= times; i++){
      sb.append(template);
      sb.append("-<");
      sb.append(i);
      sb.append(">-");
    }
    return sb.toString();
  }
}
