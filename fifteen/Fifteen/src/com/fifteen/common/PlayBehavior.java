package com.fifteen.common;

public interface PlayBehavior {
	void moveCellInDirectionIfAllowed(int aCellNumber, Direction aDirection);
	void startNewGame();
	boolean isGameFinished();
	boolean isMoveAllowedForCellInDirection(int aCell, Direction aDirection);
	boolean isMoveAllowedForCell(int aCell);

}
