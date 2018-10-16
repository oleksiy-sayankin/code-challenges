package com.fifteen.gamefields;

import static com.fifteen.common.Constants.*;

import java.util.ArrayList;

import com.fifteen.cells.AbstractMovableCell;
import com.fifteen.cells.AbstractCell;
import com.fifteen.cells.ImageMovableCell;
import com.fifteen.cells.ImmutableCell;
import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.common.Observer;

public class ImageGameField extends AbstractGameField {
  private static ImageGameField instance;

  static {
    instance = new ImageGameField();
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public static ImageGameField getInstance() {
    return instance;
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private ImageGameField() {
    int currentCellNumber = 1;

    for (int i = 0; i <= LAST_CELL_COORDINATE_Y; i++) {
      for (int j = 0; j <= LAST_CELL_COORDINATE_X; j++) {
        AbstractMovableCell newCell = new ImageMovableCell(currentCellNumber, new Coordinate(i, j));
        newCell.registerGameField(this);
        cells.add(newCell);
        currentCellNumber++;
      }
    }
    observers = new ArrayList<Observer>();
    notifyObservers();
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  public boolean isGameFinished() {
    boolean isGameFinished = true;

    int currCellNumber = 0;
    for (int i = 0; i <= LAST_CELL_COORDINATE_Y; i++) {
      for (int j = 0; j <= LAST_CELL_COORDINATE_X; j++) {
        Coordinate currCoor = new Coordinate(i, j);
        boolean isEmptyCell = false;
        currCellNumber++;
        AbstractCell currCell = new ImmutableCell(currCellNumber, isEmptyCell, currCoor);
        if (!findCellByCoordinate(currCoor).equals(currCell)) {
          isGameFinished = false;
          break;
        }
      }
    }

    return isGameFinished;
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public void mixCells() {
    for (int i = 0; i <= NUMBER_OF_STEPS_TO_MIX_CELLS - 1; i++) {
      AbstractMovableCell randomCell = findRandomCell();
      ArrayList<Direction> legalDirectionsToMoveForEmptyCell = findLegalDirectionsToMoveCell(randomCell);
      Direction randomDirection = Direction.findRandomDirectionFromList(legalDirectionsToMoveForEmptyCell);
      int cellNumber = randomCell.getNumber();
      moveCellInDirectionIfAllowed(cellNumber, randomDirection);
    }
    notifyObservers();
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public void orderCells() {
    instance = new ImageGameField();
    notifyObservers();
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  public void moveCellInDirectionIfAllowed(int aNumber, Direction aDirection) {

    AbstractMovableCell firstCell = findCellByNumber(aNumber);
    Coordinate coordinate = firstCell.getCoordinate();
    AbstractMovableCell secondCell = findNearestCellinDirection(coordinate, aDirection);
    boolean isSecondCellExists = (secondCell != null);
    if (isSecondCellExists) {
      exchangeCells(firstCell, secondCell);
    }
    notifyObservers();
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  private ArrayList<Direction> findLegalDirectionsToMoveCell(AbstractMovableCell aCell) {
    ArrayList<Direction> legalDirectionArrayList = new ArrayList<Direction>();
    for (Direction currentDirection : Direction.valuesAsArrayList()) {
      if (!aCell.isBoundInDirection(currentDirection)) {
        legalDirectionArrayList.add(currentDirection);
      }
    }
    return legalDirectionArrayList;
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  private AbstractMovableCell findRandomCell() {
    AbstractMovableCell randomCell = null;
    int randomCellNumber = (int) (Math.round((Math.random() * (TOTAL_NUMBER_OF_CELLS - 1))) + 1);
    for (AbstractMovableCell currentCell : cells) {
      if (currentCell.getNumber() == randomCellNumber) {
        randomCell = currentCell;
        break;
      }
    }
    return randomCell;
  }
}
