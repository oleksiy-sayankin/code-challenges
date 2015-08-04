package net.javacogito.labirinth2;


import java.io.*;
import java.util.Arrays;


public class Main {
    private static final int MAX_SIZE = 101;
    private static final int[][] LAB = new int[MAX_SIZE][MAX_SIZE];
    private static final int NOT_VISITED = -1;
    private static final int WALL = Integer.MIN_VALUE;
    private static final int WAY_OUT = Integer.MAX_VALUE;
    private static final int ERROR = -1;

    private static int rowsCount = 0;
    private static int columnsCount = 0;
    private static int startRow = 0;
    private static int startCol = 0;
    private static int endRow = 0;
    private static int endCol = 0;



    public static void main( String[] args ) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String inputLine;
        int row = 0;
        while ((inputLine = buffer.readLine()) != null) {
            inputLine = inputLine.trim();
            columnsCount = Math.max(columnsCount, inputLine.length());
            LAB[row] = parseLine(inputLine);
            row++;
        }
        rowsCount = row;
        startCol = findStartCol();
        endCol = findEndCol();
        endRow = rowsCount - 1;
        LAB[startRow][startCol] = 0;
        fillInWave(startRow, startCol);
        fillInPath(endRow, endCol);
        printLabArray();
    }

    public static int[] parseLine(String line){
        int result[] = new int[MAX_SIZE];
        Arrays.fill(result, NOT_VISITED);
        for (int i = 0; i <= line.length() - 1; i++){
            if (line.charAt(i) == '*'){
                result[i] = WALL;
            }
        }
        return result;
    }

    public static void fillInWave(int row, int col){
        int path = LAB[row][col];
        int newCol = col + 1;
        int newRow = row;
        if ((newCol <= columnsCount - 1) && (LAB[newRow][newCol] == NOT_VISITED || LAB[newRow][newCol] > path + 1)){
            LAB[newRow][newCol] = path + 1;
            fillInWave(newRow, newCol);
        }
        newCol = col;
        newRow = row - 1;
        if ((newRow >= 0) && (LAB[newRow][newCol] == NOT_VISITED || LAB[newRow][newCol] > path + 1)){
            LAB[newRow][newCol] = path + 1;
            fillInWave(newRow, newCol);
        }
        newCol = col - 1;
        newRow = row ;
        if ((newCol >= 0) && (LAB[newRow][newCol] == NOT_VISITED || LAB[newRow][newCol] > path + 1)){
            LAB[newRow][newCol] = path + 1;
            fillInWave(newRow, newCol);
        }
        newCol = col;
        newRow = row + 1 ;
        if ((newRow <= rowsCount - 1) && (LAB[newRow][newCol] == NOT_VISITED || LAB[newRow][newCol] > path + 1)){
            LAB[newRow][newCol] = path + 1;
            fillInWave(newRow, newCol);
        }
    }


    public static void fillInPath(int row, int col){
        int path = LAB[row][col] - 1;
        LAB[row][col] = WAY_OUT;
        if (row == 1 && col == 25){
            System.out.println();
        }
        if(row == startRow && col == startCol){
            return;
        }
        int newCol = col + 1;
        int newRow = row;
        if ((newCol <= columnsCount - 1) && LAB[newRow][newCol] == path){
            fillInPath(newRow, newCol);
            return;
        }
        newCol = col;
        newRow = row - 1;
        if ((newRow >= 0) && LAB[newRow][newCol] == path){
            fillInPath(newRow, newCol);
            return;
        }
        newCol = col - 1;
        newRow = row ;
        if ((newCol >= 0) && LAB[newRow][newCol] == path){
            fillInPath(newRow, newCol);
            return;
        }
        newCol = col;
        newRow = row + 1 ;
        if ((newRow <= rowsCount - 1) && LAB[newRow][newCol] == path){
            fillInPath(newRow, newCol);
        }
    }


    public static int findStartCol(){
        for(int j = 0; j <= columnsCount - 1; j++){
            if(LAB[0][j] == NOT_VISITED){
                return j;
            }
        }
        return ERROR;
    }

    public static int findEndCol(){
        for(int j = 0; j <= columnsCount - 1; j++){
            if(LAB[rowsCount - 1][j] == NOT_VISITED){
                return j;
            }
        }
        return ERROR;
    }

    public static void printLabArray(){
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(int i = 0; i <= rowsCount - 1; i++ ){
            if (first){
                first = false;
            } else {
                sb.append("\n");
            }
            for(int j = 0; j <= columnsCount - 1; j++) {
                switch (LAB[i][j]){
                    case WALL:
                        sb.append("*");
                        break;
                    case WAY_OUT:
                        sb.append("+");
                        break;
                    default:
                        sb.append(" ");
                }
            }
        }
        System.out.println(sb.toString());
    }
}
