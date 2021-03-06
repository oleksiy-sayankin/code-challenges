package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Implementation for table 'country' controller.
 * Contains basic CRUD operations for table 'country'.
 */

public class CountryController extends AbstractController<Country, Integer> {
  private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country";
  private static final String INSERT_COUNTRY = "INSERT INTO country (name) VALUES (?)";
  private static final String DELETE_COUNTRY = "DELETE FROM country WHERE id = ?";
  private static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM country WHERE id = ?";
  private static final String SELECT_ID_BY_COUNTRY = "SELECT id FROM country WHERE name = ?";
  private static final String UPDATE_COUNTRY_BY_ID = "UPDATE country SET name = ? WHERE id = ?";
  private static final String CREATE_COUNTRY = "CREATE TABLE country (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100))";
  private static final String DROP_COUNTRY = "DROP TABLE IF EXISTS country";
  private static final CountryController COUNTRY_CONTROLLER = new CountryController();
  private static final Logger LOG = LogManager.getLogger(CountryController.class);
  private CountryController() {}

  /**
   * Gets Country Controller
   *
   * @return Country Controller
   */
  public static CountryController getCountryController() {
    return COUNTRY_CONTROLLER;
  }

  /**
   * Returns all countries as list. Executes SELECT * FROM country.
   *
   * @return list of all elements
   */
  @Override public List<Country> getAll() {
    List<Country> countries = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_COUNTRIES);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Country country = new Country();
        country.setId(rs.getInt(1));
        country.setName(rs.getString(2));
        countries.add(country);
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    return countries;
  }

  /**
   * Updates an country.
   *
   * @param entity country to update.
   */
  @Override public Integer update(Country entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_COUNTRY_BY_ID);
    try {
      ps.setString(1, entity.getName());
      ps.setInt(2, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return entity.getId();
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d updated to new value %s.", entity.getId(), entity));
    return entity.getId();
  }

  /**
   * Returns country by it's id, usually primary key.
   *
   * @param id Id of country
   * @return result country.
   */
  @Override public Country getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_COUNTRY_BY_ID);
    Country country = new Country();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        country.setId(rs.getInt(1));
        country.setName(rs.getString(2));
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found entity %s.", country));
    return country;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @Override public Integer getIdByEntity(Country entity) {
    PreparedStatement ps = getPrepareStatement(SELECT_ID_BY_COUNTRY);
    Integer id = null;
    try {
      ps.setString(1, entity.getName());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found id %d for entity %s.",id, entity));
    return id;
  }

  /**
   * Deletes country by id.
   *
   * @param id Id of country to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_COUNTRY);
    try {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d deleted", id));
    return true;
  }

  /**
   * Inserts new country.
   *
   * @param entity country to create.
   * @return true of creation completes successfully.
   */
  @Override public Integer insert(Country entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_COUNTRY);
    try {
      ps.setString(1, entity.getName());
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return null;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity %s inserted", entity));
    return getIdByEntity(entity);
  }

  /**
   * Creates table for country.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_COUNTRY);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info("Table for entity is created");
    return true;
  }

  /**
   * Drops table with country.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_COUNTRY);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info("Table for entity is dropped");
    return true;
  }
}
