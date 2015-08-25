package com.fifteen.test;


import org.junit.Assert;
import org.junit.Test;
import com.fifteen.*;

import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;

public class CoordinateTest {
	@Test
	public void getXYTest(){
		Coordinate testCoord = new Coordinate(1,2);
		Assert.assertEquals(1, testCoord.getY());	
		Assert.assertEquals(2, testCoord.getX());	
	}
	@Test
	public void equalsTestTrue(){
		Coordinate testCoord1 = new Coordinate(1,2);
		Coordinate testCoord2 = new Coordinate(1,2);
		boolean equalCoordinate = testCoord1.equals(testCoord2);
		Assert.assertEquals(true, equalCoordinate);	
	}
	@Test
	public void equalsTestFalse(){
		Coordinate testCoord1 = new Coordinate(2,1);
		Coordinate testCoord2 = new Coordinate(1,2);
		boolean isEqualCoordinate = testCoord1.equals(testCoord2);
		Assert.assertEquals(false, isEqualCoordinate);	
	}
	@Test
	public void getNearestCoordinateInDirectionTest(){
		Coordinate testCoord = new Coordinate(5,20);
		Coordinate nearestLeftCoordinate = new Coordinate(5,19);
		Coordinate nearestRightCoordinate = new Coordinate(5,21);
		Coordinate nearestUpCoordinate = new Coordinate(4,20);
		Coordinate nearestDownCoordinate = new Coordinate(6,20);
		boolean isEqualLeftCoordinate = testCoord.findNearestCoordinateInDirection(Direction.LEFT).equals(nearestLeftCoordinate);
		boolean isEqualRightCoordinate = testCoord.findNearestCoordinateInDirection(Direction.RIGHT).equals(nearestRightCoordinate);
		boolean isEqualUpCoordinate = testCoord.findNearestCoordinateInDirection(Direction.UP).equals(nearestUpCoordinate);
		boolean isEqualDownCoordinate = testCoord.findNearestCoordinateInDirection(Direction.DOWN).equals(nearestDownCoordinate);
		Assert.assertEquals(true, isEqualLeftCoordinate);	
		Assert.assertEquals(true, isEqualRightCoordinate);	
		Assert.assertEquals(true, isEqualUpCoordinate);	
		Assert.assertEquals(true, isEqualDownCoordinate);	

	}

}
