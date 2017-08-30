package net.javacogito.balancedsmiles;

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
  public void isBalancedEmptyTest(){
    String data = "";
    Assert.assertEquals("YES", Main.isBalanced(data));
  }

  @Test
  public void isBalancedOnlyBracketsTest(){
    String data01 = "()";
    String data02 = ")(";
    String data03 = "()()()";
    String data04 = "(((()";
    String data05 = "()))))";
    String data06 = "())))(((()";
    String data07 = "(((())))()(())((()))";
    String data08 = ")";
    String data09 = "(";
    String data10 = "()(())((())))(";
    String data11 = "((((()()))))";

    Assert.assertEquals("YES", Main.isBalanced(data01));
    Assert.assertEquals("NO", Main.isBalanced(data02));
    Assert.assertEquals("YES", Main.isBalanced(data03));
    Assert.assertEquals("NO", Main.isBalanced(data04));
    Assert.assertEquals("NO", Main.isBalanced(data05));
    Assert.assertEquals("NO", Main.isBalanced(data06));
    Assert.assertEquals("YES", Main.isBalanced(data07));
    Assert.assertEquals("NO", Main.isBalanced(data08));
    Assert.assertEquals("NO", Main.isBalanced(data09));
    Assert.assertEquals("NO", Main.isBalanced(data10));
    Assert.assertEquals("YES", Main.isBalanced(data11));
  }

  @Test
  public void isBalancedBracketsWithSymbolsTest(){
    String data01 = "(khkjh)";
    String data02 = "kjh)kjhkj (khkjhj";
    String data03 = "()kjhkj(kjh)(kjhjk)";
    String data04 = "(((n()";
    String data05 = "()j  )))  )";
    String data06 = "()) ))((jhk  (()";
    String data07 = "j(((())jhkj))()(())((()))";
    String data08 = "   hh ) lkjkl";
    String data09 = "  jhk(jhjk";
    String data10 = "( )(bjk())( (())))(";
    String data11 = "(( ((( j )() ))))";

    Assert.assertEquals("YES", Main.isBalanced(data01));
    Assert.assertEquals("NO", Main.isBalanced(data02));
    Assert.assertEquals("YES", Main.isBalanced(data03));
    Assert.assertEquals("NO", Main.isBalanced(data04));
    Assert.assertEquals("NO", Main.isBalanced(data05));
    Assert.assertEquals("NO", Main.isBalanced(data06));
    Assert.assertEquals("YES", Main.isBalanced(data07));
    Assert.assertEquals("NO", Main.isBalanced(data08));
    Assert.assertEquals("NO", Main.isBalanced(data09));
    Assert.assertEquals("NO", Main.isBalanced(data10));
    Assert.assertEquals("YES", Main.isBalanced(data11));
  }



  @Test
  public void isBalancedBracketsWithSymbolsAndColonsTest(){
    String data01 = "(khk:jh)";
    String data02 = "kjh):kjhkj (:khkjhj";
    String data03 = "()kj:hkj(k:jh)(kjhjk)";
    String data04 = "(((n() ::";
    String data05 = "()j : )))  )";
    String data06 = ":()) ))((j:hk  (()";
    String data07 = "j(((())jh:kj))()(())((()))";
    String data08 = "   hh ) lk:jkl";
    String data09 = "  jhk(jh:jk";
    String data10 = "( )(bj:k())( (())))(:";
    String data11 = "(( ((( j )() )))):";
    String data12 = ":)";
    String data13 = ": )";
    String data14 = "):";
    String data15 = "( :: )";
    String data16 = "(a:a)";
    String data17 = "llkl:";
    String data18 = "(      : kl)";

    Assert.assertEquals("YES", Main.isBalanced(data01));
    Assert.assertEquals("NO", Main.isBalanced(data02));
    Assert.assertEquals("YES", Main.isBalanced(data03));
    Assert.assertEquals("NO", Main.isBalanced(data04));
    Assert.assertEquals("NO", Main.isBalanced(data05));
    Assert.assertEquals("NO", Main.isBalanced(data06));
    Assert.assertEquals("YES", Main.isBalanced(data07));
    Assert.assertEquals("NO", Main.isBalanced(data08));
    Assert.assertEquals("NO", Main.isBalanced(data09));
    Assert.assertEquals("NO", Main.isBalanced(data10));
    Assert.assertEquals("YES", Main.isBalanced(data11));
    Assert.assertEquals("YES", Main.isBalanced(data12));
    Assert.assertEquals("NO", Main.isBalanced(data13));
    Assert.assertEquals("NO", Main.isBalanced(data14));
    Assert.assertEquals("YES", Main.isBalanced(data15));
    Assert.assertEquals("YES", Main.isBalanced(data16));
    Assert.assertEquals("YES", Main.isBalanced(data17));
    Assert.assertEquals("YES", Main.isBalanced(data18));
  }


  @Test
  public void deleteColonsTest(){
    String data01 = "(:) : ojoi::):ljkljlk:::(:)(:)";
    String data02 = ":(:) : ojoi::):ljkljlk:::(:)(:)";
    String expected01 = "(:)  ojoi:)ljkljlk:(:)(:)";
    String expected02 = ":(:)  ojoi:)ljkljlk:(:)(:)";
    Assert.assertEquals(expected01, Main.deleteColons(data01));
    Assert.assertEquals(expected02, Main.deleteColons(data02));
  }

  @Test
  public void deleteSmiles(){
    String data01 = ":((";
    String data02 = "i am sick today (:()";
    String data03 = "(:)";
    String data04 = "hacker cup: started :):)";
    String data05 = ")(";

    String expected01 = "(";
    String expected02 = "i am sick today ()";
    String expected03 = "";
    String expected04 = "hacker cup: started ";
    String expected05 = ")(";

    Assert.assertEquals(expected01, Main.deleteSmiles(data01));
    Assert.assertEquals(expected02, Main.deleteSmiles(data02));
    Assert.assertEquals(expected03, Main.deleteSmiles(data03));
    Assert.assertEquals(expected04, Main.deleteSmiles(data04));
    Assert.assertEquals(expected05, Main.deleteSmiles(data05));
  }


}
