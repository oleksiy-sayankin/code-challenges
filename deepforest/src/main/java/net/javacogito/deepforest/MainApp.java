package net.javacogito.deepforest;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Date;

/**
 * @author oleksiy sayankin
 */

public class MainApp {
  private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

  public static void main(String[] args) throws Exception {
    PropertyConfigurator.configure("log4j.properties");
    long startTime = new Date().getTime();
    String inputFileName = "i.in";
    String outputFileName = "i.out";
    InputData inputData = new InputData();
    inputData.read(new FileReader(inputFileName));
    OutputData outputData = SinIntervalDataProcessor.process(inputData);
    outputData.write(new FileWriter(outputFileName));
    long endTime = new Date().getTime();
    LOGGER.info(" duration = " + (endTime - startTime) + " ms");
  }
}
