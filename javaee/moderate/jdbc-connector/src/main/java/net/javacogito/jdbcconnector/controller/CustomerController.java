package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'customer' controller.
 * Contains basic CRUD operations for table 'customer'.
 */

public class CustomerController extends AbstractController<Customer, Integer> {
  private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
  private static final String INSERT_CUSTOMER = "INSERT INTO customer VALUES (?, ?, ?, ?)";
  private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE id = ?";
  private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE id = ?";
  private static final String UPDATE_CUSTOMER_BY_ID = "UPDATE customer SET company = ?, address = ?, country_id = ? WHERE id = ?";
  private static final String CREATE_CUSTOMER = "CREATE TABLE customer (id INT PRIMARY KEY, company VARCHAR(100), address VARCHAR(100), country_id INT)";
  private static final String DROP_CUSTOMER = "DROP TABLE IF EXISTS customer";
  private static final CustomerController CUSTOMER_CONTROLLER = new CustomerController();
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
        customer.setCountryId(rs.getInt(4));
        customers.add(customer);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return customers;
  }

  /**
   * Updates an customer.
   *
   * @param entity customer to update.
   */
  @Override public boolean update(Customer entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_CUSTOMER_BY_ID);
    try {
      ps.setString(1, entity.getCompany());
      ps.setString(2, entity.getAddress());
      ps.setInt(3, entity.getCountryId());
      ps.setInt(4, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
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
        customer.setCountryId(rs.getInt(4));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return customer;
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
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Inserts new customer.
   *
   * @param entity customer to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(Customer entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_CUSTOMER);
    try {
      ps.setInt(1, entity.getId());
      ps.setString(2, entity.getCompany());
      ps.setString(3, entity.getAddress());
      ps.setInt(4, entity.getCountryId());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
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
      return false;
    } finally {
      closePrepareStatement(ps);
    }
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
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }
}
