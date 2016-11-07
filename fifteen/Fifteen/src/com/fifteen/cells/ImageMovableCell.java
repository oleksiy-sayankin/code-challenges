package com.fifteen.cells;

import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.gamefields.ImageGameField;

public class ImageMovableCell extends AbstractMovableCell {
  //---------------------------------------------------------------------------------------------------------------------------------------
  public ImageMovableCell(int aNumber, Coordinate aCoordinate) {
    super(aNumber, false, (Coordinate) aCoordinate.clone());
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  public boolean isMoveAllowedInDirection(Direction aDirection) {
    boolean result = false;
    if (aDirection != null) {
      Coordinate coordinate = getCoordinate();
      Coordinate newCoordinate = coordinate.findNearestCoordinateInDirection(aDirection);
      if (((ImageGameField) gameField).isCoordinateInGameField(newCoordinate)) {
        result = true;
      }
    }
    return result;
  }
}
