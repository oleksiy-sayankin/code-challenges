package net.javacogito.deepforest;

import net.javacogito.math.ICircle;
import net.javacogito.math.IPoint;
import net.javacogito.math.IRay;
import net.javacogito.math.Ray;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author oleksiy sayankin
 */
public final class Util {
  private Util() {
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

  @Before
  public void init() {
    PropertyConfigurator.configure("log4j.properties");
  }

  /**
   * Empty and delete a folder (and subfolders).
   *
   * @param folder folder to empty
   */
  public static void rmdir(final File folder) {
    // check if folder file is a real folder
    if (folder.isDirectory()) {
      File[] list = folder.listFiles();
      if (list != null) {
        for (int i = 0; i < list.length; i++) {
          File tmpF = list[i];
          if (tmpF.isDirectory()) {
            rmdir(tmpF);
          }
          tmpF.delete();
        }
      }
      if (!folder.delete()) {
        LOGGER.info("can't delete folder : " + folder);
      }
    }
  }

  public static Set<IPoint> intersectionPoints(ICircle[] circles, IRay ray) {
    Set<IPoint> result = new HashSet<IPoint>();
    for (ICircle circle : circles) {
      Set<IPoint> intersectionPoints = circle.intersection(ray);
      if (!intersectionPoints.isEmpty()) {
        result.addAll(intersectionPoints);
        LOGGER.info(circle + ", " + intersectionPoints);
      }
    }
    return result;
  }

  public static boolean isIntersections(InputData inputData, OutputData outputData) {
    IRay ray = new Ray(inputData.getInitPos(), outputData.getExit());
    Set<IPoint> intersectionPoints = intersectionPoints(inputData.getCircles(), ray);
    return !intersectionPoints.isEmpty();
  }
}
