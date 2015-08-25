package com.fifteen.common;


public class Coordinate implements Cloneable{
   private int x; 
   private int y;
   
   //----------------------------------------------------------------------------------------------------------------------------------------
   public Coordinate(int ay, int ax){
	   x = ax;
	   y = ay;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   public Coordinate(){
	   x = 0;
	   y = 0;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   Coordinate(Coordinate aCoordinate){
	   x = aCoordinate.getX();
	   y = aCoordinate.getY();
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   public int getX(){
	   return x;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   public int getY(){
	   return y;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   public void setX(int ax){
	   x = ax;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   public void setY(int ay){
	   y = ay;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   @Override
   public boolean  equals(Object aObject){
	   Coordinate coordinate = (Coordinate)aObject;
	   boolean result = (x == coordinate.getX()) && (y == coordinate.getY());
	   return result;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   @Override
   public int hashCode(){
	   return  x + y; 
   }
   
   //----------------------------------------------------------------------------------------------------------------------------------------
   public Coordinate findNearestCoordinateInDirection(Direction aDirection){
	   Coordinate coordinate = new Coordinate();
	   switch(aDirection){
	   case LEFT:
		   coordinate.setX(x - 1);
		   coordinate.setY(y);
		   break;
	   case RIGHT:
		   coordinate.setX(x + 1);
		   coordinate.setY(y);
		   break;
	   case UP:
		   coordinate.setX(x);
		   coordinate.setY(y - 1);
		   break;
	   case DOWN:
		   coordinate.setX(x);
		   coordinate.setY(y + 1);
		   break;
	   }
	   return coordinate;
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   @Override
   public Object clone(){
	   return new Coordinate(y,x); 
   }
   //----------------------------------------------------------------------------------------------------------------------------------------
   @Override
   public String toString(){
	   return "[" + y + "," + x + "]"; 
   }
   
}
