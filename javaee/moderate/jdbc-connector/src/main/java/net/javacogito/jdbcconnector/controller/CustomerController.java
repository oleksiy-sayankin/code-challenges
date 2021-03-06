package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static net.javacogito.jdbcconnector.controller.CountryController.getCountryController;

/**
 * Implementation for table 'customer' controller.
 * Contains basic CRUD operations for table 'customer'.
 */

public class CustomerController extends AbstractController<Customer, Integer> {
  private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
  private static final String INSERT_CUSTOMER = "INSERT INTO customer(company, address, country_id) VALUES (?, ?, ?)";
  private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id = ?";
  private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";
  private static final String SELECT_ID_BY_CUSTOMER = "SELECT id FROM customer WHERE company = ? AND address = ? AND country_id = ?";
  private static final String UPDATE_CUSTOMER_BY_ID = "UPDATE customer SET company = ?, address = ?, country_id = ? WHERE id = ?";
  private static final String CREATE_CUSTOMER = "CREATE TABLE customer (id INT AUTO_INCREMENT PRIMARY KEY, company VARCHAR(100), address VARCHAR(100), country_id INT)";
  private static final String DROP_CUSTOMER = "DROP TABLE IF EXISTS customer";
  private static final CustomerController CUSTOMER_CONTROLLER = new CustomerController();
  private static final CountryController COUNTRY_CONTROLLER = getCountryController();
  private static final Logger LOG = LogManager.getLogger(CustomerController.class);
  private CustomerController() {}

  /**
   * Gets Customer Controller
   *
   * @return Customer Controller
   */
  public static CustomerController getCustomerController() {
    return CUSTOMER_CONTROLLER;
  }


  /**
   * Returns all customers as list. Executes SELECT * FROM customer.
   *
   * @return list of all elements
   */
  @Override public List<Customer> getAll() {
    List<Customer> customers = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_CUSTOMERS);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Customer customer = new Customer();
        customer.setId(rs.getInt(1));
        customer.setCompany(rs.getString(2));
        customer.setAddress(rs.getString(3));
        customer.setCountry(COUNTRY_CONTROLLER.getEntityById(rs.getInt(4)));
        customers.add(customer);
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    return customers;
  }

  /**
   * Updates an customer cascade.
   *
   * @param entity customer to update.
   */
  @Override public Integer update(Customer entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_CUSTOMER_BY_ID);
    int countryId = COUNTRY_CONTROLLER.instertOrUpdateIfExists(entity.getCountry());
    try {
      ps.setString(1, entity.getCompany());
      ps.setString(2, entity.getAddress());
      ps.setInt(3, countryId);
      ps.setInt(4, entity.getId());
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
   * Returns customer by it's id, usually primary key.
   *
   * @param id Id of customer
   * @return result customer.
   */
  @Override public Customer getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_CUSTOMER_BY_ID);
    Customer customer = new Customer();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        customer.setId(rs.getInt(1));
        customer.setCompany(rs.getString(2));
        customer.setAddress(rs.getString(3));
        customer.setCountry(COUNTRY_CONTROLLER.getEntityById(rs.getInt(4)));
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found entity %s.", customer));
    return customer;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @Override public Integer getIdByEntity(Customer entity) {
    PreparedStatement ps = getPrepareStatement(SELECT_ID_BY_CUSTOMER);
    Integer id = null;
    try {
      ps.setString(1, entity.getCompany());
      ps.setString(2, entity.getAddress());
      ps.setInt(3, entity.getCountry().getId());
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
   * Deletes customer by id.
   *
   * @param id Id of customer to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_CUSTOMER);
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
   * Inserts new customer.
   *
   * @param entity customer to create.
   * @return true of creation completes successfully.
   */
  @Override public Integer insert(Customer entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_CUSTOMER);
    int countryId = COUNTRY_CONTROLLER.instertOrUpdateIfExists(entity.getCountry());
    try {
      ps.setString(1, entity.getCompany());
      ps.setString(2, entity.getAddress());
      ps.setInt(3, countryId);
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
   * Creates table for customer.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_CUSTOMER);
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
   * Drops table with customer.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_CUSTOMER);
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
