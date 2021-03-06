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
  public void replaceSingleSmileTest(){
    String data01 = "(khk:jh):)";
    String data02 = "(k:)hk:jh):)";
    String data03 = "aaa:((k:)hk:jh):)";
    Assert.assertEquals("(khk:jh)Z", Main.replaceSingleSmile(data01));
    Assert.assertEquals("(kZhk:jh):)", Main.replaceSingleSmile(data02));
    Assert.assertEquals("aaaZ(k:)hk:jh):)", Main.replaceSingleSmile(data03));
  }


  @Test
  public void replaceColonFromSmileTest(){
    String data01 = "(khk:jh):)";
    String data02 = "(k:)hk:jh):)";
    String data03 = "aaa:((k:)hk:jh):)";
    Assert.assertEquals("(khk:jh)Z)", Main.replaceColonFromSmile(data01));
    Assert.assertEquals("(kZ)hk:jh):)", Main.replaceColonFromSmile(data02));
    Assert.assertEquals("aaaZ((k:)hk:jh):)", Main.replaceColonFromSmile(data03));
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
    String data19 = "(      :)";
    String data20 = ":((";
    String data21 = "i am sick today (:()";
    String data22 = "(:)";
    String data23 = "hacker cup: started :):)";
    String data24 = ")(";
    String data25 = "(  abc::::::)";
    String data26 = ":):):(:(";
    String data27 = "(  :::  )";
    String data28 = "::::::::";
    String data29 = "    ";
    String data30 = "((((((((((";
    String data31 = "))))))))))";
    String data32 = "(abcd:))";
    String data33 = "(abcd:))(aaaa)aaaa:)(((aaa:))))(:)(aaa:)";

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
    Assert.assertEquals("YES", Main.isBalanced(data19));
    Assert.assertEquals("NO", Main.isBalanced(data20));
    Assert.assertEquals("YES", Main.isBalanced(data21));
    Assert.assertEquals("YES", Main.isBalanced(data22));
    Assert.assertEquals("YES", Main.isBalanced(data23));
    Assert.assertEquals("NO", Main.isBalanced(data24));
    Assert.assertEquals("YES", Main.isBalanced(data25));
    Assert.assertEquals("YES", Main.isBalanced(data26));
    Assert.assertEquals("YES", Main.isBalanced(data27));
    Assert.assertEquals("YES", Main.isBalanced(data28));
    Assert.assertEquals("YES", Main.isBalanced(data29));
    Assert.assertEquals("NO", Main.isBalanced(data30));
    Assert.assertEquals("NO", Main.isBalanced(data31));
    Assert.assertEquals("YES", Main.isBalanced(data32));
    Assert.assertEquals("YES", Main.isBalanced(data33));
  }
}
