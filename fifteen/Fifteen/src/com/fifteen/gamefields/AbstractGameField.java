package com.fifteen.gamefields;

import static com.fifteen.common.Constants.LAST_CELL_COORDINATE_X;
import static com.fifteen.common.Constants.LAST_CELL_COORDINATE_Y;

import java.util.ArrayList;
import java.util.HashSet;

import com.fifteen.cells.AbstractMovableCell;
import com.fifteen.cells.ImmutableCell;
import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.common.Observer;
import com.fifteen.common.Subject;

public abstract class AbstractGameField extends CellsContainer<AbstractMovableCell> implements Subject {
  protected ArrayList<Observer> observers;

  public abstract void mixCells();

  public abstract void orderCells();

  public abstract void moveCellInDirectionIfAllowed(int aNumber, Direction aDirection);

  public abstract boolean isGameFinished();


  //-----------------------------------------------------------------------------------------------------------------------------------------
  public AbstractGameField() {
    super(new HashSet<AbstractMovableCell>());
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public GameFieldState findState() {
    HashSet<ImmutableCell> cellsState = new HashSet<ImmutableCell>();
    for (AbstractMovableCell currentCell : cells) {
      int number = currentCell.getNumber();
      boolean isEmpty = currentCell.isEmpty();
      Coordinate coordinate = currentCell.getCoordinate();
      cellsState.add(new ImmutableCell(number, isEmpty, coordinate));
    }
    return new GameFieldState(cellsState);
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public boolean isCoordinateInGameField(Coordinate aCoordinate) {
    boolean result = false;

    result = (aCoordinate.getX() >= 0) && (aCoordinate.getY() >= 0) && (aCoordinate.getX() <= LAST_CELL_COORDINATE_X) && (aCoordinate.getY() <= LAST_CELL_COORDINATE_Y);

    return result;
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public boolean isMoveAllowedForCellInDirection(int aNumber, Direction aDirection) {
    return findCellByNumber(aNumber).isMoveAllowedInDirection(aDirection);
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  public boolean isMoveAllowedForCell(int aNumber) {
    return findCellByNumber(aNumber).isMoveAllowed();
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  protected void exchangeCells(AbstractMovableCell firstCell, AbstractMovableCell secondCell) {
    Coordinate tempCoordinate = firstCell.getCoordinate();
    firstCell.setCoordinate(secondCell.getCoordinate());
    secondCell.setCoordinate(tempCoordinate);
  }

  //------------------------------------------------------------------------------------------------------------------------------------------
  public void registerObserver(Observer aObserver) {
    observers.add(aObserver);

  }

  //------------------------------------------------------------------------------------------------------------------------------------------
  public void removeObserver(Observer aObserver) {
    int i = observers.indexOf(aObserver);
    if (i >= 0) {
      observers.remove(i);
    }
  }

  //------------------------------------------------------------------------------------------------------------------------------------------
  public void notifyObservers() {
    for (Observer currentOserver : observers) {
      currentOserver.update(findState());
    }
  }
}
