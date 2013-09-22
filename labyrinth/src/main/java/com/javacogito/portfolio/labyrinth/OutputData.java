package com.javacogito.portfolio.labyrinth;

import java.io.IOException;
import java.io.Writer;

/**
 * @author oleksiy sayankin
 */
public class OutputData {
    private Path path;
    private static OutputData instance = new OutputData();
    private OutputData(){}

    public static OutputData getInstance(){
        return instance;
    }

    public void write(Writer writer) throws IOException {
        try{
        if(path.isEmpty()){
            writer.write(Constant.NO_EXIT);
        }else{
            writer.write(path.toDirectionsString());
        }
        writer.flush();
        }finally {
            writer.close();
        }
    }

    public void setPath(Path path){
        this.path = path;
    }
}
