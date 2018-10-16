package com.fifteen.cells;

import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.exceptions.CoordinateOutOfGameFieldException;
import com.fifteen.gamefields.ClassicGameField;

public class ClassicMovableCell extends AbstractMovableCell {
  //---------------------------------------------------------------------------------------------------------------------------------------
  public ClassicMovableCell(int aNumber, boolean aIsEmpty, Coordinate aCoordinate) {
    super(aNumber, aIsEmpty, (Coordinate) aCoordinate.clone());
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  public boolean isMoveAllowedInDirection(Direction aDirection) {
    boolean result = false;
    if (aDirection != null) {
      Coordinate coordinate = getCoordinate();
      Coordinate newCoordinate = coordinate
        .findNearestCoordinateInDirection(aDirection);
      try {
        if (((ClassicGameField) gameField).isEmptyCellAtCoordinate(newCoordinate)) {
          result = true;
        }
      } catch (CoordinateOutOfGameFieldException e) {
        result = false;
      }
    }
    return result;
  }
}
