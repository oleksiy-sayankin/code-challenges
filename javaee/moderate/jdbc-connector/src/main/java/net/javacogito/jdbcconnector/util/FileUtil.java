package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.context.EnvContext;

import java.io.File;

/**
 * File operation utility class.
 *
 */
public final class FileUtil {
  private FileUtil(){}

  /**
   * Gets file from $HOME/data folder.
   *
   * @param fileName file name.
   * @return file from $HOME/data.
   */
  public static File getFromDataDir(String fileName){
    return new File(new EnvContext().getHome() + File.separator + "data" + File.separator + fileName);
  }
}
