package com.fifteen.common;

import java.util.ArrayList;
import java.util.List;


public enum Direction {
   LEFT, RIGHT, UP, DOWN;
   //----------------------------------------------------------------------------------------------------------------------------------
   public static Direction findRandomDirectionFromList(List<Direction> aDirectionArrayList){
	   int directionCount = aDirectionArrayList.size();
	   int randomIndex = (int) Math.round(Math.random() * (directionCount - 1));
	   return aDirectionArrayList.get(randomIndex);
   }
   //----------------------------------------------------------------------------------------------------------------------------------
   public static ArrayList<Direction> valuesAsArrayList(){
	   ArrayList<Direction> directionArrayList = new ArrayList<Direction>();
	   directionArrayList.add(LEFT);
	   directionArrayList.add(RIGHT);
	   directionArrayList.add(UP);
	   directionArrayList.add(DOWN);
	   return directionArrayList;
   }   
}
