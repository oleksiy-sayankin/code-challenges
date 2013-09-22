package com.javacogito.portfolio.deepforest;

import com.javacogito.portfolio.math.IPoint;
import com.javacogito.portfolio.math.Point;

import java.io.Reader;
import java.io.Writer;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalDataProcessor {
    private SinIntervalDataProcessor() {
    }

    public static OutputData process(InputData inputData) {
        IPoint initPos = inputData.getInitPos();
        SinIntervalAggregator agg = new SinIntervalAggregator(inputData);
        SinInterval freeSinInterval = agg.freeSinInterval();
        OutputData outputData = new OutputData();
        if (freeSinInterval == null) {
            outputData.setForestIsDeep(true);
        } else {
            outputData.setForestIsDeep(false);
            Sin sin = freeSinInterval.innerSin();

            Quadrant quadrant = sin.getQuadrant();
            double sinValue = sin.getSinValue();
            double cosValue = Quadrant.cosSign(quadrant) * Math.sqrt(1 - sinValue * sinValue);
            double x = Constants.DEFAULT_RADIUS * cosValue + initPos.getX();
            double y = Constants.DEFAULT_RADIUS * sinValue + initPos.getY();
            IPoint result = new Point(x, y);
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
