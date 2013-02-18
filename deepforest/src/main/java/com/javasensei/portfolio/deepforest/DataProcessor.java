package com.javasensei.portfolio.deepforest;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class DataProcessor {
    private DataProcessor(){}
    public static void process(Reader inputFileReader, Writer outputFileWriter) throws Exception {
        InputData inputData = new InputData();
        inputData.read(inputFileReader);
        List<Circle> circles =  inputData.getCircles();
        Coord initPos = inputData.getInitPos();
        Aggregator agg = new Aggregator();
        for(Circle circle : circles){
            agg.add(MathHelper.shadow(circle, initPos));
        }
        List<CircleSegment> freeSegments = agg.freeSegments();
        OutputData outputData = new OutputData();
        if(freeSegments.isEmpty()){
            outputData.setForestIsDeep(true);
        }else {
            outputData.setForestIsDeep(false);
            double alpha = freeSegments.get(0).midPoint();
            double x = Constants.DEFAULT_RADIUS * Math.cos(alpha) + initPos.x();
            double y = Constants.DEFAULT_RADIUS * Math.sin(alpha) + initPos.y();
            Coord result =  new Coord(x, y);
            outputData.setExit(result);
        }
        outputData.write(outputFileWriter);
    }
}
