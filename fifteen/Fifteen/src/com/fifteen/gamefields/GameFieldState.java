package com.fifteen.gamefields;

import java.util.HashSet;
import java.util.Iterator;

import com.fifteen.cells.ImmutableCell;
import com.fifteen.common.Coordinate;

import static com.fifteen.common.Constants.*;

public class GameFieldState extends CellsContainer<ImmutableCell> implements Iterable<ImmutableCell>{

	//------------------------------------------------------------------------------------------------------------------------------------------
	public GameFieldState(HashSet<ImmutableCell> aCells){
		super(aCells);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------
	public ImmutableCell findCellByNumber(int aCellNumber){
		return super.findCellByNumber(aCellNumber);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean equals(Object aObject){
		boolean result = true;
		GameFieldState aGameFieldState = (GameFieldState)aObject;
		for(int i = 1; i <= TOTAL_NUMBER_OF_CELLS; i++){
			ImmutableCell thisCell = this.findCellByNumber(i);
			ImmutableCell anotherCell = aGameFieldState.findCellByNumber(i);			
			if(!thisCell.equals(anotherCell)){
				result = false;
				break;
			}
		}
		return result;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public int hashCode(){
		int hashCode = 0;
		for(int i = 1; i <= TOTAL_NUMBER_OF_CELLS; i++){
			ImmutableCell thisCell = this.findCellByNumber(i);
			hashCode += thisCell.hashCode();
		}
		return hashCode;
	} 
	//----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String toString(){ 
		String gameFieldString = "";
				
		for (int i = 0; i <= LAST_CELL_COORDINATE_Y; i++){
			for(int j = 0; j <= LAST_CELL_COORDINATE_X; j++){
			Coordinate currCellCoord = new Coordinate(i,j);
			ImmutableCell currCell = findCellByCoordinate(currCellCoord);
			String stringToPrintAsCell = "";
			if (currCell.isEmpty()){
				stringToPrintAsCell = "*" + "\t"; 
			}
			else {
				stringToPrintAsCell = currCell.getNumber() + "\t"; 
			}
			gameFieldString += stringToPrintAsCell;
			}
			gameFieldString += "\n";
		}
		
		return gameFieldString; 
	}
	//----------------------------------------------------------------------------------------------------------------------------------------	
	public ImmutableCell findCellByCoordinate(Coordinate aCoordinate){
		return super.findCellByCoordinate(aCoordinate);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------	
	public Iterator<ImmutableCell> iterator() {
		return super.iterator();
	}
}
