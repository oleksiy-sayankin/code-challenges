package net.javacogito.jdbcconnector.util;

import java.io.File;

/**
 * File operation utility class.
 *
 */
public final class FileUtil {
  private FileUtil(){}

  /**
   * Gets file from resources.
   *
   * @param fileName file name.
   * @return file from resources.
   */
  public static File getFromResources(String fileName){
    ClassLoader classLoader = FileUtil.class.getClassLoader();
    return new File(classLoader.getResource(fileName).getFile());
  }
}
