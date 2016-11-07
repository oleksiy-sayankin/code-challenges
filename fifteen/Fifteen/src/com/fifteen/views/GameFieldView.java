package com.fifteen.views;

import java.util.ArrayList;

import com.fifteen.cells.AbstractCell;
import com.fifteen.cells.ImmutableCell;
import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.common.Observer;
import com.fifteen.controllers.CurrentGameController;
import com.fifteen.gamefields.GameFieldState;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static com.fifteen.common.Constants.*;

public class GameFieldView extends View implements Observer, View.OnTouchListener {
  private ArrayList<CellView> cells;
  private GameFieldState gameFieldState;
  private final String TAG = "GameFieldView";
  private boolean isCellsCreated = false;
  private CellView touchedCellView;
  private boolean isCellDragged;
  private float shiftOfCellX;
  private float shiftOfCellY;
  private float startX;
  private float startY;
  private float currentX;
  private float currentY;
  private Direction currentDirection;
  private ArrayList<Direction> allowedDirections;

  //------------------------------------------------------------------------------------------------------------------------
  public GameFieldView(Context context) {
    super(context);
    setOnTouchListener(this);
    cells = new ArrayList<CellView>();
    allowedDirections = new ArrayList<Direction>();
  }

  //------------------------------------------------------------------------------------------------------------------------
  public void update(GameFieldState aGameFieldState) {
    updateGameFieldState(aGameFieldState);
    if (isCellsCreated == false) {
      createCellViews();
    }
    updateCellViews();
  }

  //------------------------------------------------------------------------------------------------------------------------
  private void updateGameFieldState(GameFieldState aGameFieldState) {
    gameFieldState = aGameFieldState;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private void updateCellViews() {
    for (AbstractCell currentCell : gameFieldState) {
      int cellNumber = currentCell.getNumber();
      CellView cellView = findCellViewByNumber(cellNumber);
      Coordinate coordinate = currentCell.getCoordinate();
      float x = coordinate.getX() * cellView.getWidth();
      float y = coordinate.getY() * cellView.getHeight();
      cellView.setXY(x, y);
    }
  }

  //------------------------------------------------------------------------------------------------------------------------
  private void createCellViews() {
    Resources resources = getResources();
    for (AbstractCell currentCell : gameFieldState) {
      Coordinate coordinate = currentCell.getCoordinate();
      int cellNumber = currentCell.getNumber();
      CellView cellView = new CellView(resources, cellNumber);
      float x = coordinate.getX() * cellView.getWidth();
      float y = coordinate.getY() * cellView.getHeight();
      cellView.setXY(x, y);
      cells.add(cellView);
    }

    isCellsCreated = true;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private CellView findCellViewByNumber(int aNumber) {
    CellView cellView = null;
    for (CellView currCellView : cells) {
      if (currCellView.getNumber() == aNumber) {
        cellView = currCellView;
        break;
      }
    }
    return cellView;
  }

  //------------------------------------------------------------------------------------------------------------------------
  @Override
  public void onDraw(Canvas canvas) {
    for (CellView currCellView : cells) {
      currCellView.draw(canvas);
    }
  }

  //------------------------------------------------------------------------------------------------------------------------
  public boolean onTouch(View view, MotionEvent event) {
    currentX = event.getX();
    currentY = event.getY();
    Log.i(TAG, "x = " + currentX + ", y = " + currentY);
    if (isCellDragged == false) {
      if (isOneOfCellsTouched()) {
        touchedCellView = findTouchedCellView();
      } else {
        return false;
      }
    }
    float cellX;
    float cellY;
    int action = event.getAction();
    int currCellNumber = touchedCellView.getNumber();
    switch (action) {
      case MotionEvent.ACTION_DOWN:
        touchedCellView.setIsPressed(true);
        cellX = touchedCellView.getX();
        cellY = touchedCellView.getY();
        updateStartXY();
        updateShiftOfCell(cellX, cellY);
        findAllowedDirections();
        isCellDragged = true;
        break;
      case MotionEvent.ACTION_MOVE:
        currentDirection = findDirection();
        Log.i(TAG, "currentDirection = " + currentDirection);
        Log.i(TAG, "isMoveAllowedForCell = " + CurrentGameController.getInstance().isMoveAllowedForCell(currCellNumber));
        Log.i(TAG, "isDirectionExists() = " + isDirectionExists());
        if (isDirectionExists() && CurrentGameController.getInstance().isMoveAllowedForCell(currCellNumber)) {
          cellX = currentX - shiftOfCellX;
          cellY = currentY - shiftOfCellY;
          float adjustedCellX = adjustCoorXInDirectionForCellView(cellX, currentDirection);
          float adjustedCellY = adjustCoorYInDirectionForCellView(cellY, currentDirection);
          touchedCellView.setXY(adjustedCellX, adjustedCellY);
          bringToFrontCellWithNumber(currCellNumber);
        }
        break;
      case MotionEvent.ACTION_UP:
        touchedCellView.setIsPressed(false);
        isCellDragged = false;
        CurrentGameController.getInstance().moveCellInDirectionIfAllowed(currCellNumber, currentDirection);
        break;
    }
    Log.i(TAG, "action = " + action);
    invalidate();
    return true;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private float adjustCoorXInDirectionForCellView(float aCellX, Direction aDirection) {
    float ajustedX = aCellX;
    float leftBound = 0;
    float rightBound = 0;
    ImmutableCell touchedCell = gameFieldState.findCellByNumber(touchedCellView.getNumber());

    if (allowedDirections.contains((aDirection))) {
      if (aDirection.equals(Direction.UP) || aDirection.equals(Direction.DOWN)) {
        leftBound = touchedCell.getCoordinate().getX() * touchedCellView.getWidth();
        rightBound = leftBound;
      }
      if (aDirection.equals(Direction.RIGHT)) {
        leftBound = touchedCell.getCoordinate().getX() * touchedCellView.getWidth();
        rightBound = leftBound + touchedCellView.getWidth();
      }
      if (aDirection.equals(Direction.LEFT)) {
        rightBound = touchedCell.getCoordinate().getX() * touchedCellView.getWidth();
        leftBound = rightBound - touchedCellView.getWidth();
      }
    } else {
      leftBound = touchedCell.getCoordinate().getX() * touchedCellView.getWidth();
      rightBound = leftBound;
    }
    if (aCellX > rightBound) {
      ajustedX = rightBound;
    }
    if (aCellX < leftBound) {
      ajustedX = leftBound;
    }

    return ajustedX;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private float adjustCoorYInDirectionForCellView(float aCellY, Direction aDirection) {
    float ajustedY = aCellY;
    float upBound = 0;
    float downBound = 0;
    ImmutableCell touchedCell = gameFieldState.findCellByNumber(touchedCellView.getNumber());
    if (allowedDirections.contains((aDirection))) {
      if (aDirection.equals(Direction.LEFT) || aDirection.equals(Direction.RIGHT)) {
        upBound = touchedCell.getCoordinate().getY() * touchedCellView.getHeight();
        downBound = upBound;
      }
      if (aDirection.equals(Direction.DOWN)) {
        upBound = touchedCell.getCoordinate().getY() * touchedCellView.getHeight();
        downBound = upBound + touchedCellView.getHeight();
      }
      if (aDirection.equals(Direction.UP)) {
        downBound = touchedCell.getCoordinate().getY() * touchedCellView.getHeight();
        upBound = downBound - touchedCellView.getHeight();
      }
    } else {
      upBound = touchedCell.getCoordinate().getY() * touchedCellView.getHeight();
      downBound = upBound;
    }
    if (aCellY > downBound) {
      ajustedY = downBound;
    }
    if (aCellY < upBound) {
      ajustedY = upBound;
    }
    return ajustedY;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private Direction findDirection() {
    Direction result = null;
    float deltaX = currentX - startX;
    float deltaY = currentY - startY;
    if (Math.abs(deltaX) >= Math.abs(deltaY)) {
      if (deltaX > 0) {
        result = Direction.RIGHT;
      } else {
        result = Direction.LEFT;
      }
    } else {
      if (deltaY > 0) {
        result = Direction.DOWN;
      } else {
        result = Direction.UP;
      }

    }
    return result;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private boolean isDirectionExists() {
    float deltaX = currentX - startX;
    float deltaY = currentY - startY;
    return !((deltaX == 0) && (deltaY == 0));
  }

  //------------------------------------------------------------------------------------------------------------------------
  private void updateStartXY() {
    startX = currentX;
    startY = currentY;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private CellView findTouchedCellView() {
    CellView result = null;
    for (CellView currentCellView : cells) {
      if (currentCellView.isTouched(currentX, currentY)) {
        result = currentCellView;
        break;
      }
    }
    return result;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private boolean isOneOfCellsTouched() {
    boolean result = false;
    for (CellView currentCellView : cells) {
      if (currentCellView.isTouched(currentX, currentY)) {
        result = true;
        break;
      }
    }
    return result;

  }

  //------------------------------------------------------------------------------------------------------------------------
  private void updateShiftOfCell(float aCellX, float aCellY) {
    shiftOfCellX = currentX - aCellX;
    shiftOfCellY = currentY - aCellY;
  }

  //------------------------------------------------------------------------------------------------------------------------
  private void findAllowedDirections() {
    allowedDirections.clear();
    int cellNumber = touchedCellView.getNumber();
    for (Direction currentDirection : Direction.valuesAsArrayList()) {
      if (CurrentGameController.getInstance().isMoveAllowedForCellInDirection(cellNumber, currentDirection)) {
        allowedDirections.add(currentDirection);
      }
    }
  }

  //------------------------------------------------------------------------------------------------------------------------
  private void bringToFrontCellWithNumber(int aNumber) {
    final int LAST_CELL_INDEX = TOTAL_NUMBER_OF_CELLS - 1;
    for (int i = 0; i <= LAST_CELL_INDEX; i++) {
      if (cells.get(i).getNumber() == aNumber) {
        CellView tempCellView = cells.get(i);
        cells.set(i, cells.get(LAST_CELL_INDEX));
        cells.set(LAST_CELL_INDEX, tempCellView);
        break;
      }
    }
  }
} 

