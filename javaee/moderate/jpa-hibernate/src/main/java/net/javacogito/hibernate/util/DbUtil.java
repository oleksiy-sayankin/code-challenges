package net.javacogito.hibernate.util;


import net.javacogito.hibernate.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * Utility class to create / drop all tables in DB.
 */
public final class DbUtil {
  private static final Logger LOG = LogManager.getLogger(DbUtil.class);

  private DbUtil(){}

  /**
   * Stores entity into DB
   *
   * @param entities list of entities
   */
  @SuppressWarnings("unchecked")
  public static void storeInDb(List<? extends Entity> entities){
    for (Entity entity : entities){
      entity.getController().insert(entity);
    }
  }
}
