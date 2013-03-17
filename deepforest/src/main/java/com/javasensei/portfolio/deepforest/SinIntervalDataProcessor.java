package com.javasensei.portfolio.deepforest;

import com.javasensei.portfolio.math.IArc;
import com.javasensei.portfolio.math.ICircle;
import com.javasensei.portfolio.math.IPoint;
import com.javasensei.portfolio.math.Point;

import java.io.Reader;
import java.io.Writer;
import java.util.List;

/**
 * @author oleksiy sayankin
 */
public class SinIntervalDataProcessor {
    private SinIntervalDataProcessor() {
    }

    public static OutputData process(InputData inputData) {
        IPoint initPos = inputData.getInitPos();
        SinIntervalAggregator agg = new SinIntervalAggregator(inputData);
        List<SinInterval> freeSegments = agg.freeSinIntervals();
        OutputData outputData = new OutputData();
        if (freeSegments.isEmpty()) {
            outputData.setForestIsDeep(true);
        } else {
            outputData.setForestIsDeep(false);
            Sin sin = freeSegments.get(0).innerSin();

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
