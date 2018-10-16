package com.fifteen.test;

import static com.fifteen.common.Constants.LAST_CELL_COORDINATE_X;
import static com.fifteen.common.Constants.LAST_CELL_COORDINATE_Y;
import static com.fifteen.common.Constants.TOTAL_NUMBER_OF_CELLS;

import org.junit.Assert;
import org.junit.Test;

import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.gamefields.GameFieldState;
import com.fifteen.gamefields.ImageGameField;

public class ImageGameFieldTest {
  @Test
  public void mixCellsTest() {

    ImageGameField testGameField = ImageGameField.getInstance();

    GameFieldState completedState = testGameField.findState();

    testGameField.mixCells();

    GameFieldState mixedState = testGameField.findState();

    boolean isCellsMixed = !(mixedState.equals(completedState));

    System.out.println("Before order");
    System.out.println(mixedState);
    testGameField.orderCells();
    System.out.println("After order");
    System.out.println(completedState);

    Assert.assertEquals(true, isCellsMixed);
  }

  @Test
  public void getCompletedGameFieldTest() {
    ImageGameField testGameField = ImageGameField.getInstance();
    testGameField.orderCells();
    System.out.println(testGameField.findState());
    int lastCellNumber = TOTAL_NUMBER_OF_CELLS - 1;
    testGameField.moveCellInDirectionIfAllowed(lastCellNumber, Direction.RIGHT);

    boolean result = testGameField.isGameFinished();

    Assert.assertEquals(false, result);
  }

  @Test
  public void isCoordinateInGameFieldTest() {
    ImageGameField testGameField = ImageGameField.getInstance();
    testGameField.orderCells();
    Coordinate coor1 = new Coordinate(LAST_CELL_COORDINATE_Y + 1, LAST_CELL_COORDINATE_X + 1);
    Coordinate coor2 = new Coordinate(-1, 0);
    Coordinate coor3 = new Coordinate(0, 0);
    boolean isCoorInGameField1 = testGameField.isCoordinateInGameField(coor1);
    boolean isCoorInGameField2 = testGameField.isCoordinateInGameField(coor2);
    boolean isCoorInGameField3 = testGameField.isCoordinateInGameField(coor3);
    boolean isCorrectWork = (!isCoorInGameField1) && (!isCoorInGameField2) && (isCoorInGameField3);
    Assert.assertEquals(true, isCorrectWork);
  }

  @Test
  public void isGameFinishedTest() {
    ImageGameField testGameField = ImageGameField.getInstance();
    testGameField.orderCells();
    boolean isGameFinished = testGameField.isGameFinished();
    Assert.assertEquals(true, isGameFinished);
  }

  @Test
  public void moveCellTest() {
    ImageGameField testGameField = ImageGameField.getInstance();
    testGameField.orderCells();
    int lastCellNumber = TOTAL_NUMBER_OF_CELLS - 1;
    testGameField.moveCellInDirectionIfAllowed(lastCellNumber, Direction.RIGHT);
    int[] currCellNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 15};
    int currIndx = 0;
    boolean isError = false;
    for (int i = 0; i <= LAST_CELL_COORDINATE_Y; i++) {
      for (int j = 0; j <= LAST_CELL_COORDINATE_X; j++) {
        Coordinate currCoor = new Coordinate(i, j);
        boolean isCellNumbersEqual = (currCellNumbers[currIndx] == testGameField.findState().findCellByCoordinate(currCoor).getNumber());
        currIndx++;
        if (!isCellNumbersEqual) {
          isError = true;
          break;
        }
      }
    }

    System.out.println(testGameField.findState());

    Assert.assertEquals(false, isError);
  }

}
