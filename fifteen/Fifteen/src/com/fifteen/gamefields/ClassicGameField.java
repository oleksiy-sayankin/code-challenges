package com.fifteen.gamefields;

import java.util.ArrayList;

import com.fifteen.cells.AbstractMovableCell;
import com.fifteen.cells.AbstractCell;
import com.fifteen.cells.ClassicMovableCell;
import com.fifteen.cells.ImmutableCell;
import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.common.Observer;
import com.fifteen.exceptions.CoordinateOutOfGameFieldException;

import static com.fifteen.common.Constants.*;

public class ClassicGameField extends AbstractGameField {
    private static ClassicGameField instance;
    static{ instance = new ClassicGameField();}
	//-----------------------------------------------------------------------------------------------------------------------------------------	
    public static ClassicGameField getInstance(){
    	return instance;
    }
	//-----------------------------------------------------------------------------------------------------------------------------------------	
	private ClassicGameField(){
		int currentCellNumber = 1;
				
		for(int i = 0; i <= LAST_CELL_COORDINATE_Y; i++){
			for(int j = 0; j <= LAST_CELL_COORDINATE_X; j++){
				boolean iSLastCell = (i == LAST_CELL_COORDINATE_Y) && (j == LAST_CELL_COORDINATE_X);
				boolean iSEmpty = iSLastCell; 
				AbstractMovableCell newCell = new ClassicMovableCell(currentCellNumber, iSEmpty, new Coordinate(i,j));
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
		for(int i = 0; i <= LAST_CELL_COORDINATE_Y; i++){
			for(int j = 0; j <= LAST_CELL_COORDINATE_X; j++){
				Coordinate currCoor = new Coordinate(i,j);
				boolean isEmptyCell = (i == LAST_CELL_COORDINATE_Y) && (j == LAST_CELL_COORDINATE_X);
				currCellNumber++; 
				AbstractCell currCell = new ImmutableCell(currCellNumber, isEmptyCell, currCoor);
				if(!findCellByCoordinate(currCoor).equals(currCell)){
					isGameFinished = false;
					break;
				}
			}
		}		
		return isGameFinished;
	}	
	//-----------------------------------------------------------------------------------------------------------------------------------------	
	public void mixCells() {
		for(int i = 0; i <= NUMBER_OF_STEPS_TO_MIX_CELLS - 1; i++){
			ArrayList<Direction> legalDirectionsToMoveForEmptyCell = findLegalDirectionsToMoveForEmptyCell();
			Direction randomDirection = Direction.findRandomDirectionFromList(legalDirectionsToMoveForEmptyCell);
			moveEmptyCellInDirection(randomDirection);
		}		
		notifyObservers();
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------	
	public void orderCells(){
		instance = new ClassicGameField();
		notifyObservers();
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------	
	public boolean isEmptyCellAtCoordinate(Coordinate aCoordinate)
			throws CoordinateOutOfGameFieldException {
		if (isCoordinateInGameField(aCoordinate) == false) {
			throw new CoordinateOutOfGameFieldException();
		}
		
		AbstractMovableCell cellWithCoordinate = findCellByCoordinate(aCoordinate);
		
		return cellWithCoordinate.isEmpty();
	}


	//----------------------------------------------------------------------------------------------------------------------------------------	
	public	void moveCellInDirectionIfAllowed(int aNumber, Direction aDirection){
		
		AbstractMovableCell firstCell = findCellByNumber(aNumber); 
		Coordinate coordinate = firstCell.getCoordinate(); 
		AbstractMovableCell secondCell = findNearestCellinDirection(coordinate, aDirection);
		boolean isSecondCellExists = (secondCell != null);
		if (isSecondCellExists && secondCell.isEmpty()){
			exchangeCells(firstCell, secondCell);
			}	
		notifyObservers();
	}

	//----------------------------------------------------------------------------------------------------------------------------------------	
	private ArrayList<Direction> findLegalDirectionsToMoveForEmptyCell(){
		ArrayList<Direction> legalDirectionArrayList = new ArrayList<Direction>();
		AbstractMovableCell emptyCell = findEmptyCell();
		for (Direction currentDirection : Direction.valuesAsArrayList()){
			if (!emptyCell.isBoundInDirection(currentDirection)){
				legalDirectionArrayList.add(currentDirection);
			}
		}
		return legalDirectionArrayList;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------	
	private AbstractMovableCell findEmptyCell(){
		AbstractMovableCell emptyCell = null;
		for(AbstractMovableCell currentCell : cells){
			if(currentCell.isEmpty()){
				emptyCell = currentCell;
				break;
			}
		}
		return emptyCell;
	}

	//----------------------------------------------------------------------------------------------------------------------------------------	
	private void moveEmptyCellInDirection(Direction aDirection)  {
		
		AbstractMovableCell emptyCell = findEmptyCell(); 
		assert(emptyCell.isBoundInDirection(aDirection)==false):"Can`t move empty cell in direction " + aDirection;
		AbstractMovableCell secondCell = findNearestCellinDirection(emptyCell.getCoordinate(), aDirection);
		exchangeCells(emptyCell, secondCell);
	}
	
}
