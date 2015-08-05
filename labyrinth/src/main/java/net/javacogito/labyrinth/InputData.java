package net.javacogito.labyrinth;

import java.io.*;

/**
 *  @author oleksiy sayankin
 */
public class InputData {
    private Coord initPos;
    private Labyrinth labyrinth;
    private static InputData instance = new InputData();
    private InputData(){}

    public static InputData getInstance(){
        return instance;
    }

    public Coord getInitPos(){
        return initPos;
    }
    public Labyrinth getLabyrinth(){
        return labyrinth;
    }
    public void read(Reader reader) throws Exception {
        BufferedReader br = new BufferedReader(reader);
        String firstLine = br.readLine();
        String[] labyrinthSize = firstLine.split(",");
        int labyrinthHeight = Integer.valueOf(labyrinthSize[0]);
        int labyrinthWidth = Integer.valueOf(labyrinthSize[1]);
        if(labyrinthHeight <= 0){
            throw new Exception("Illegal value for labyrinth height : " + labyrinthHeight);
        }
        if(labyrinthWidth <= 0){
            throw new Exception("Illegal value for labyrinth width : " + labyrinthWidth);
        }
        labyrinth = new Labyrinth(labyrinthHeight, labyrinthWidth);
        String secondLine = br.readLine();
        String[] pos = secondLine.split(",");
        int yPos = Integer.valueOf(pos[0]);
        int xPos = Integer.valueOf(pos[1]);
        if(yPos <= 0 || yPos >= labyrinthHeight){
            throw new Exception("Illegal value for y start position : " + yPos);
        }
        if(xPos <= 0 || xPos >= labyrinthWidth){
            throw new Exception("Illegal value for x start position : " + xPos);
        }
        initPos = new Coord(yPos,xPos);
        for(int i = 0; i <= labyrinthHeight - 1; i++){
            String line = br.readLine();
            labyrinth.addRow(i, line);
        }
    }

}
