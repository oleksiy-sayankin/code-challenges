package com.fifteen.test;

import org.junit.Assert;
import org.junit.Test;
import com.fifteen.*;
import com.fifteen.cells.AbstractMovableCell;
import com.fifteen.cells.ClassicMovableCell;
import com.fifteen.common.Coordinate;


public class ClassicCellTest {
	@Test
	public void equalsTestTrue(){
		final int CELL_NUMBER = 0;	
		final boolean IS_EMPTY = false;
		Coordinate coord1 = new Coordinate(4,58);
		Coordinate coord2 = new Coordinate(4,58);
		AbstractMovableCell testCell1 = new ClassicMovableCell(CELL_NUMBER, IS_EMPTY, coord1);
		AbstractMovableCell testCell2 = new ClassicMovableCell(CELL_NUMBER, IS_EMPTY, coord2);
		boolean isCellEquals = testCell1.equals(testCell2); 
		Assert.assertEquals(true, isCellEquals);	
	}
	@Test 
	public void equalsTestFalse(){
		final int CELL_NUMBER = 0;	
		final boolean IS_EMPTY = false;
		Coordinate coord1 = new Coordinate(3,58);
		Coordinate coord2 = new Coordinate(4,58);
		AbstractMovableCell testCell1 = new ClassicMovableCell(CELL_NUMBER, IS_EMPTY, coord1);
		AbstractMovableCell testCell2 = new ClassicMovableCell(CELL_NUMBER, IS_EMPTY, coord2);
		boolean isCellEquals = testCell1.equals(testCell2); 
		Assert.assertEquals(false, isCellEquals);	
	}
	@Test
	public void getCoordinateTest(){
		final int CELL_NUMBER = 0;	
		final boolean IS_EMPTY = false;
		Coordinate coord1 = new Coordinate(4,58);
		Coordinate coord2 = new Coordinate(4,58);
		AbstractMovableCell testCell = new ClassicMovableCell(CELL_NUMBER, IS_EMPTY, coord1);
		boolean result = testCell.getCoordinate().equals(coord2);
		Assert.assertEquals(true, result);			
	}

}