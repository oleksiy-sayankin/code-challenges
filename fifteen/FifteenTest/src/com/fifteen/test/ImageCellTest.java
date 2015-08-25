package com.fifteen.test;

import org.junit.Assert;
import org.junit.Test;

import com.fifteen.cells.AbstractMovableCell;
import com.fifteen.cells.ImageMovableCell;
import com.fifteen.common.Coordinate;

public class ImageCellTest {
	@Test
	public void equalsTestTrue(){
		final int CELL_NUMBER = 0;	
		Coordinate coord1 = new Coordinate(4,58);
		Coordinate coord2 = new Coordinate(4,58);
		AbstractMovableCell testCell1 = new ImageMovableCell(CELL_NUMBER, coord1);
		AbstractMovableCell testCell2 = new ImageMovableCell(CELL_NUMBER, coord2);
		boolean isCellEquals = testCell1.equals(testCell2); 
		Assert.assertEquals(true, isCellEquals);	
	}
	@Test 
	public void equalsTestFalse(){
		final int CELL_NUMBER = 0;	
		Coordinate coord1 = new Coordinate(3,58);
		Coordinate coord2 = new Coordinate(4,58);
		AbstractMovableCell testCell1 = new ImageMovableCell(CELL_NUMBER, coord1);
		AbstractMovableCell testCell2 = new ImageMovableCell(CELL_NUMBER, coord2);
		boolean isCellEquals = testCell1.equals(testCell2); 
		Assert.assertEquals(false, isCellEquals);	
	}
	@Test
	public void getCoordinateTest(){
		final int CELL_NUMBER = 0;	
		Coordinate coord1 = new Coordinate(4,58);
		Coordinate coord2 = new Coordinate(4,58);
		AbstractMovableCell testCell = new ImageMovableCell(CELL_NUMBER, coord1);
		boolean result = testCell.getCoordinate().equals(coord2);
		Assert.assertEquals(true, result);			
	}

}
