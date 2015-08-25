package com.fifteen.gamefields;

import java.util.HashSet;
import java.util.Iterator;

import com.fifteen.cells.AbstractCell;
import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;

public abstract class CellsContainer<CELLTYPE extends AbstractCell> {
	protected final HashSet<CELLTYPE> cells;
	//------------------------------------------------------------------------------------------------------------------------------------------
	protected CellsContainer(HashSet<CELLTYPE> aCells){
		cells = aCells;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------
	protected CELLTYPE findCellByNumber(int aCellNumber){
		CELLTYPE cell = null;  
		for(CELLTYPE currentCell : cells){
			if (currentCell.getNumber() == aCellNumber){
				cell = currentCell;
				break;
			}
		}
		return cell;
	}
	//----------------------------------------------------------------------------------------------------------------------------------------	
	protected CELLTYPE findCellByCoordinate(Coordinate aCoordinate){
		CELLTYPE cellWithCoordinate = null;
		for(CELLTYPE currentCell: cells ){
			if(currentCell.getCoordinate().equals(aCoordinate)){
				cellWithCoordinate = currentCell;
				break;
			}
		}
		return cellWithCoordinate;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------	
	protected CELLTYPE findNearestCellinDirection(Coordinate aCoordinate,
			Direction aDirection) {
		CELLTYPE nearestCellinDirection = null;
		Coordinate nearestCellCoordinate = aCoordinate.findNearestCoordinateInDirection(aDirection);
		nearestCellinDirection = findCellByCoordinate(nearestCellCoordinate);
		return nearestCellinDirection;		
	}
	//----------------------------------------------------------------------------------------------------------------------------------------	
	protected boolean hasNearestCellinDirection(Coordinate aCoordinate,
			Direction aDirection) {
		boolean result;
		Coordinate nearestCellCoordinate = aCoordinate.findNearestCoordinateInDirection(aDirection);
		result = findCellByCoordinate(nearestCellCoordinate)!=null;
		return result;		
	}
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------	
	protected Iterator<CELLTYPE> iterator() {
		return cells.iterator();
	}
}
