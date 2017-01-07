package com.fifteen.controllers;

import com.fifteen.common.Direction;
import com.fifteen.common.Observer;
import com.fifteen.common.PlayBehavior;
import com.fifteen.common.Subject;
import com.fifteen.gamefields.AbstractGameField;


public abstract class AbstractGameController implements PlayBehavior, Subject {
  protected static AbstractGameController instance;
  protected static AbstractGameField gameField;

  //-----------------------------------------------------------------------------------------------------------------------
  public void moveCellInDirectionIfAllowed(int aCellNumber, Direction aDirection) {
    gameField.moveCellInDirectionIfAllowed(aCellNumber, aDirection);
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public void startNewGame() {
    gameField.mixCells();
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public boolean isGameFinished() {
    return gameField.isGameFinished();
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public boolean isMoveAllowedForCellInDirection(int aCell, Direction aDirection) {
    return gameField.isMoveAllowedForCellInDirection(aCell, aDirection);
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public boolean isMoveAllowedForCell(int aNumber) {
    return gameField.isMoveAllowedForCell(aNumber);
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public void registerObserver(Observer aObserver) {
    gameField.registerObserver(aObserver);
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public void removeObserver(Observer aObserver) {
    gameField.removeObserver(aObserver);
  }

  //-----------------------------------------------------------------------------------------------------------------------
  public void notifyObservers() {
    gameField.notifyObservers();
  }
}
