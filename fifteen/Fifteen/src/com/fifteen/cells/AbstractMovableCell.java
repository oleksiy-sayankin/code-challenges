package com.fifteen.cells;

import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.gamefields.AbstractGameField;

public abstract class AbstractMovableCell extends AbstractCell{ 
 
	protected AbstractGameField gameField; 
	//---------------------------------------------------------------------------------------------------------------------------------------
	public abstract boolean isMoveAllowedInDirection(Direction aDirection); 
    //---------------------------------------------------------------------------------------------------------------------------------------
	public AbstractMovableCell(int aNumber, boolean aIsEmpty, Coordinate aCoordinate){
		super(aNumber, aIsEmpty, (Coordinate)aCoordinate.clone());
	}	
	//---------------------------------------------------------------------------------------------------------------------------------------
	public void registerGameField(AbstractGameField aGameField) {
		gameField = aGameField;
		
	}
	//---------------------------------------------------------------------------------------------------------------------------------------
	public boolean isMoveAllowed() {
		boolean result = false;
		for(Direction currDirection : Direction.values()){
			if(isMoveAllowedInDirection(currDirection)){
				result = true;
				break;
			}
		}
		return result;		
	}	
	//---------------------------------------------------------------------------------------------------------------------------------------
	public void setCoordinate(Coordinate aCoordinate) {
		super.setCoordinate(aCoordinate);		 
	}  
	//---------------------------------------------------------------------------------------------------------------------------------------
	public boolean isBoundInDirection(Direction aDirection) {
		Coordinate coordinate = getCoordinate();
		Coordinate newCoordinate = coordinate.findNearestCoordinateInDirection(aDirection);
		return  !gameField.isCoordinateInGameField(newCoordinate);		 
	}
}
