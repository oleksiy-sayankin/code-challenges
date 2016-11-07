package com.fifteen.cells;

import com.fifteen.common.Coordinate;

public abstract class AbstractCell {
  //---------------------------------------------------------------------------------------------------------------------------------------
  protected AbstractCell(int aNumber, boolean aIsEmpty, Coordinate aCoordinate) {
    number = aNumber;
    isEmpty = aIsEmpty;
    coordinate = (Coordinate) aCoordinate.clone();
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  public Coordinate getCoordinate() {
    return (Coordinate) coordinate.clone();
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  public int getNumber() {
    return number;
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public boolean equals(Object aObject) {
    boolean result = false;
    AbstractCell cell = (AbstractCell) aObject;
    boolean isSameCoordinate = cell.getCoordinate().equals(coordinate);
    boolean isSameNumber = cell.getNumber() == number;
    boolean isSameEmpty = cell.isEmpty() == isEmpty;
    if (isSameCoordinate && isSameNumber && isSameEmpty) {
      result = true;
    }
    return result;
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return coordinate.hashCode() + number;
  }

  //----------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "{" + number + "," + isEmpty + "," + coordinate + "}";
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  protected void setCoordinate(Coordinate aCoordinate) {
    coordinate = aCoordinate;
  }

  //---------------------------------------------------------------------------------------------------------------------------------------
  public boolean isEmpty() {
    return isEmpty;
  }

  private Coordinate coordinate;
  private final int number;
  private final boolean isEmpty;

}
