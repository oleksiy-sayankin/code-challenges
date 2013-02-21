package com.javasensei.portfolio.deepforest;

import java.io.IOException;
import java.io.Writer;

/**
 * @author oleksiy sayankin
 */
public class OutputData {
    private boolean forestIsDeep;
    private Coord exit;



    public void write(Writer writer) throws IOException {
        try{
            if(forestIsDeep){
                writer.write(Constants.YES);
            }else{
                writer.write(Constants.NO);
                writer.write("\n");
                writer.write(exit.x() + " " + exit.y());
            }
            writer.flush();
        }finally {
            writer.close();
        }
    }
    public void setForestIsDeep(boolean forestIsDeep){
        this.forestIsDeep = forestIsDeep;
    }

    public void setExit(Coord exit){
       this.exit = exit;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(!(other instanceof OutputData)){
            return false;
        }
        if(this == other){
            return  true;
        }
        OutputData otherOutputData = (OutputData)other;
        return this.forestIsDeep == otherOutputData.forestIsDeep;
    }

    @Override
    public int hashCode(){
        return (forestIsDeep ? 31 : 32) * exit.hashCode();
    }

    @Override
    public String toString(){
        return (forestIsDeep ? Constants.YES : Constants.NO) + ", " + exit;
    }
}
