import java.io.IOException;
import java.io.Writer;

/**
 * @author oleksiy sayankin
 */
public class OutputData {
    private boolean forestIsDeep;
    private Coord exit;
    private static OutputData instance = new OutputData();
    private OutputData(){}

    public static OutputData getInstance(){
        return instance;
    }
    public void write(Writer writer) throws IOException {
        try{
            if(forestIsDeep){
                writer.write(Constants.YES);
            }else{
                writer.write(Constants.NO);
                writer.write(exit.toString());
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
}
