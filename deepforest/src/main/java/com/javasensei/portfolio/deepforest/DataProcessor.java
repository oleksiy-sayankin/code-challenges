package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.*;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class DataProcessor {
    private DataProcessor(){}

    public static OutputData process(InputData inputData){
        List<ICircle> circles =  inputData.getCircles();
        IPoint initPos = inputData.getInitPos();
        CircleAggregator agg = new CircleAggregator(inputData);
        List<IArc> freeSegments = agg.freeArcs();
        OutputData outputData = new OutputData();
        if(freeSegments.isEmpty()){
            outputData.setForestIsDeep(true);
        }else {
            outputData.setForestIsDeep(false);
            double alpha = freeSegments.get(0).midAngle();
            double x = Constants.DEFAULT_RADIUS * Math.cos(alpha) + initPos.getX();
            double y = Constants.DEFAULT_RADIUS * Math.sin(alpha) + initPos.getY();
            IPoint result =  new Point(x, y);
            outputData.setExit(result);
        }
        return outputData;
    }

    public static void process(Reader inputFileReader, Writer outputFileWriter) throws Exception {
        InputData inputData = new InputData();
        inputData.read(inputFileReader);
        process(inputData).write(outputFileWriter);
    }
}
