import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class DataProcessor {
    private DataProcessor(){}
    public static void process(Reader inputFileReader, Writer outputFileWriter) throws Exception {
        InputData.getInstance().read(inputFileReader);
        List<Circle> circles =  InputData.getInstance().getCircles();
        Coord initPos = InputData.getInstance().getInitPos();
        Aggregator agg = new Aggregator();
        for(Circle circle : circles){
            agg.add(MathHelper.shadow(circle, initPos));
        }
        List<CircleSegment> freeSegments = agg.freeSegments();
        if(freeSegments.isEmpty()){
            OutputData.getInstance().setForestIsDeep(true);
        }else {
            OutputData.getInstance().setForestIsDeep(false);
            double alpha = freeSegments.get(0).midPoint();
            double x = Constants.DEFAULT_RADIUS * Math.cos(alpha);
            double y = Constants.DEFAULT_RADIUS * Math.sin(alpha);
            OutputData.getInstance().setExit(new Coord(x, y));
        }
        OutputData.getInstance().write(outputFileWriter);
    }
}
