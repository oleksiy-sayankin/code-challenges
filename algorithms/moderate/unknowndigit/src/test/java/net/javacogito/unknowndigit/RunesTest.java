package net.javacogito.unknowndigit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RunesTest {

  @Test
  public void testParseFirstArg() {
    assertEquals(4, Runes.parseFirstArg("4*4=16"));
    assertEquals(-4, Runes.parseFirstArg("-4*-4=16"));

    assertEquals(123, Runes.parseFirstArg("123*4=168"));
    assertEquals(423, Runes.parseFirstArg("423-5=168"));
    assertEquals(4263, Runes.parseFirstArg("4263+5=168"));

    assertEquals(-123, Runes.parseFirstArg("-123*4=168"));
    assertEquals(-423, Runes.parseFirstArg("-423-5=168"));
    assertEquals(-4263, Runes.parseFirstArg("-4263+5=168"));

  }

  @Test
  public void testParseSecondArg() {
    assertEquals(4, Runes.parseSecondArg("4*4=16"));

    assertEquals(123, Runes.parseSecondArg("173*123=168"));
    assertEquals(423, Runes.parseSecondArg("3-423=168"));
    assertEquals(4263, Runes.parseSecondArg("2763+4263=168"));

    assertEquals(-123, Runes.parseSecondArg("-123654*-123=168"));
    assertEquals(423, Runes.parseSecondArg("-63-423=168"));
    assertEquals(-4263, Runes.parseSecondArg("-422363+-4263=168"));
  }

  @Test
  public void testParseResult(){
    assertEquals(16, Runes.parseResult("4*4=16"));
    assertEquals(-168, Runes.parseResult("123*4=-168"));
  }

  @Test
  public void testParseOperation() {
    assertEquals('*', Runes.parseOperation("4*4=16"));

    assertEquals('*', Runes.parseOperation("173*123=168"));
    assertEquals('-', Runes.parseOperation("3-423=168"));
    assertEquals('+', Runes.parseOperation("2763+4263=168"));

    assertEquals('*', Runes.parseOperation("-123654*-123=168"));
    assertEquals('-', Runes.parseOperation("-63-423=168"));
    assertEquals('+', Runes.parseOperation("-422363+-4263=168"));
  }

  @Test
  public  void testIsValid(){
    assertTrue(Runes.isValid("4*4=16"));
    assertTrue(Runes.isValid("-4*-4=16"));
    assertTrue(Runes.isValid("-4*4=-16"));
    assertTrue(Runes.isValid("4*-4=-16"));
  }

  @Test
  public void testSample() {
    assertEquals( "Answer for expression '1+1=?' " , 2 , Runes.solveExpression("1+1=?") );
    assertEquals( "Answer for expression '123*45?=5?088' " , 6 , Runes.solveExpression("123*45?=5?088") );
    assertEquals( "Answer for expression '-5?*-1=5?' " , 0 , Runes.solveExpression("-5?*-1=5?") );
    assertEquals( "Answer for expression '19--45=5?' " , -1 , Runes.solveExpression("19--45=5?") );
    assertEquals( "Answer for expression '??*??=302?' " , 5 , Runes.solveExpression("??*??=302?") );
    assertEquals( "Answer for expression '?*11=??' " , 2 , Runes.solveExpression("?*11=??") );
    assertEquals( "Answer for expression '??*1=??' " , 2 , Runes.solveExpression("??*1=??") );
    assertEquals( "Answer for expression '??+??=??' " , -1 , Runes.solveExpression("??+??=??") );
    assertEquals( "Answer for expression '?*1=?' " , 0 , Runes.solveExpression("?*1=?") );
  }

}
