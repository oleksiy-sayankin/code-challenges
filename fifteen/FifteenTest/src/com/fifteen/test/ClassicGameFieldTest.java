package com.fifteen.test;


import org.junit.Assert;
import org.junit.Test;
import com.fifteen.*;
import com.fifteen.common.Coordinate;
import com.fifteen.common.Direction;
import com.fifteen.exceptions.CoordinateOutOfGameFieldException;
import com.fifteen.gamefields.ClassicGameField;
import com.fifteen.gamefields.GameFieldState;

import static com.fifteen.common.Constants.*;

public class ClassicGameFieldTest {

	@Test
	public void mixCellsTest() {
		
		ClassicGameField testGameField = ClassicGameField.getInstance();
		
		GameFieldState completedState = testGameField.findState(); 
		
		testGameField.mixCells(); 

		GameFieldState mixedState = testGameField.findState(); 
				
		boolean isCellsMixed = !(mixedState.equals(completedState));
				
		System.out.println("Before order");
		System.out.println(mixedState);
		testGameField.orderCells();
		System.out.println("After order");
		System.out.println(completedState);
				
		Assert.assertEquals(true, isCellsMixed);
	}
	@Test
    public void getCompletedGameFieldTest(){
		ClassicGameField testGameField = ClassicGameField.getInstance();
		testGameField.orderCells();
		System.out.println(testGameField.findState());
		int lastCellNumber = TOTAL_NUMBER_OF_CELLS - 1;
		testGameField.moveCellInDirectionIfAllowed(lastCellNumber, Direction.RIGHT);

		boolean result = testGameField.isGameFinished();

		Assert.assertEquals(false, result);
	}
	@Test
	public void isCoordinateInGameFieldTest(){
		ClassicGameField testGameField = ClassicGameField.getInstance();
		testGameField.orderCells();
		Coordinate coor1 = new Coordinate(LAST_CELL_COORDINATE_Y + 1, LAST_CELL_COORDINATE_X + 1);
		Coordinate coor2 = new Coordinate(-1, 0);
		Coordinate coor3 = new Coordinate(0, 0);
		boolean isCoorInGameField1 = testGameField.isCoordinateInGameField(coor1);
		boolean isCoorInGameField2 = testGameField.isCoordinateInGameField(coor2);
		boolean isCoorInGameField3 = testGameField.isCoordinateInGameField(coor3);
		boolean isCorrectWork = (! isCoorInGameField1) && (! isCoorInGameField2) && (isCoorInGameField3); 
		Assert.assertEquals(true, isCorrectWork);		
	}
	@Test
	public void isEmptyCellAtCoordinateTest(){
		ClassicGameField testGameField = ClassicGameField.getInstance();
		testGameField.orderCells();
		Coordinate coor1 = new Coordinate(LAST_CELL_COORDINATE_Y, LAST_CELL_COORDINATE_X);
		Coordinate coor2 = new Coordinate(LAST_CELL_COORDINATE_Y - 1, LAST_CELL_COORDINATE_X - 1);
		Coordinate coor3 = new Coordinate(LAST_CELL_COORDINATE_Y + 1, LAST_CELL_COORDINATE_X);
		boolean isEmptyCell1 = false;
		boolean isEmptyCell2 = false;
		boolean isEmptyCell3 = false;
		try {
			isEmptyCell1 = testGameField.isEmptyCellAtCoordinate(coor1);
			isEmptyCell2 = testGameField.isEmptyCellAtCoordinate(coor2);
			isEmptyCell3 = testGameField.isEmptyCellAtCoordinate(coor3);
		} catch (CoordinateOutOfGameFieldException e) {
			 isEmptyCell2 = false;
			 isEmptyCell3 = false;
		} 
		boolean isCorrectWork = (isEmptyCell1) && (! isEmptyCell2) && (! isEmptyCell3);
		Assert.assertEquals(true, isCorrectWork);		
	}
	@Test
	public void isGameFinishedTest(){
		ClassicGameField testGameField = ClassicGameField.getInstance();
		testGameField.orderCells();
		boolean isGameFinished = testGameField.isGameFinished(); 
		Assert.assertEquals(true, isGameFinished);		
	}
	@Test
	public void moveCellTest(){
		ClassicGameField testGameField = ClassicGameField.getInstance();
		testGameField.orderCells();
		int lastCellNumber = TOTAL_NUMBER_OF_CELLS - 1;
		testGameField.moveCellInDirectionIfAllowed(lastCellNumber, Direction.RIGHT);
		final int EMPTY_CELL_NUMBER = TOTAL_NUMBER_OF_CELLS; 
		int[] currCellNumbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,EMPTY_CELL_NUMBER,15};
		int currIndx = 0;
		boolean isError = false;
		for(int i = 0; i <= LAST_CELL_COORDINATE_Y; i++){
			for(int j = 0; j <= LAST_CELL_COORDINATE_X; j++){
				Coordinate currCoor = new Coordinate(i,j);
				boolean isEmptyCell = (currCellNumbers[currIndx] == EMPTY_CELL_NUMBER);
				currIndx++; 
				try {
					if(testGameField.isEmptyCellAtCoordinate(currCoor) && !isEmptyCell){
						isError = true;
						break;
					}
				} catch (CoordinateOutOfGameFieldException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(testGameField.findState());

		Assert.assertEquals(false, isError);						
	}
}
