package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.EmployeePhone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'employee_phone' controller.
 * Contains basic CRUD operations for table 'employee_phone'.
 */

public class EmployeePhoneController extends AbstractController<EmployeePhone, Integer> {
  private static final String SELECT_ALL_EMPLOYEE_PHONES = "SELECT * FROM employee_phone";
  private static final String INSERT_EMPLOYEE_PHONE = "INSERT INTO employee_phone(employee_id, number) VALUES (?, ?)";
  private static final String DELETE_EMPLOYEE_PHONE = "DELETE FROM employee_phone WHERE id = ?";
  private static final String SELECT_EMPLOYEE_PHONE_BY_ID = "SELECT * FROM employee_phone WHERE id = ?";
  private static final String SELECT_ID_BY_EMPLOYEE_PHONE = "SELECT id FROM employee_phone WHERE number = ?";
  private static final String UPDATE_EMPLOYEE_PHONE_BY_ID = "UPDATE employee_phone SET employee_id = ?, number = ? WHERE id = ?";
  private static final String CREATE_EMPLOYEE_PHONE = "CREATE TABLE employee_phone (id INT AUTO_INCREMENT PRIMARY KEY, employee_id INT, number VARCHAR(100))";
  private static final String DROP_EMPLOYEE_PHONE = "DROP TABLE IF EXISTS employee_phone";
  private static final EmployeePhoneController EMPLOYEE_PHONE_CONTROLLER = new EmployeePhoneController();
  private static final Logger LOG = LogManager.getLogger(EmployeePhoneController.class);
  private EmployeePhoneController() {}

  /**
   * Gets Employee Phone Controller
   *
   * @return Employee Phone Controller
   */
  public static EmployeePhoneController getEmployeePhoneController() {
    return EMPLOYEE_PHONE_CONTROLLER;
  }

  /**
   * Returns all employee phones as list. Executes SELECT * FROM employee_phone.
   *
   * @return list of all elements
   */
  @Override public List<EmployeePhone> getAll() {
    List<EmployeePhone> employeePhones = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_EMPLOYEE_PHONES);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        EmployeePhone employeePhone = new EmployeePhone();
        employeePhone.setId(rs.getInt(1));
        employeePhone.setEmployeeId(rs.getInt(2));
        employeePhone.setNumber(rs.getString(3));
        employeePhones.add(employeePhone);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return employeePhones;
  }

  /**
   * Updates an employee phone.
   *
   * @param entity employee phone to update.
   */
  @Override public boolean update(EmployeePhone entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_EMPLOYEE_PHONE_BY_ID);
    try {
      ps.setInt(1, entity.getEmployeeId());
      ps.setString(2, entity.getNumber());
      ps.setInt(3, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d updated to new value %s.", entity.getId(), entity));
    return true;
  }

  /**
   * Returns employee phone by it's id, usually primary key.
   *
   * @param id Id of employee phone
   * @return result employee phone.
   */
  @Override public EmployeePhone getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_EMPLOYEE_PHONE_BY_ID);
    EmployeePhone employeePhone = new EmployeePhone();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        employeePhone.setId(rs.getInt(1));
        employeePhone.setEmployeeId(rs.getInt(2));
        employeePhone.setNumber(rs.getString(3));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found entity %s.", employeePhone));
    return employeePhone;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @Override public Integer getIdByEntity(EmployeePhone entity) {
    PreparedStatement ps = getPrepareStatement(SELECT_ID_BY_EMPLOYEE_PHONE);
    Integer id = null;
    try {
      ps.setString(1, entity.getNumber());
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        id = rs.getInt(1);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found id %d for entity %s.",id, entity));
    return id;
  }

  /**
   * Deletes employee phone by id.
   *
   * @param id Id of employee phone to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_EMPLOYEE_PHONE);
    try {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d deleted", id));
    return true;
  }

  /**
   * Inserts new employee phone.
   *
   * @param entity employee phone to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(EmployeePhone entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_EMPLOYEE_PHONE);
    try {
      ps.setInt(1, entity.getEmployeeId());
      ps.setString(2, entity.getNumber());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity %s inserted", entity));
    return true;
  }

  /**
   * Creates table for employee phone.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_EMPLOYEE_PHONE);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info("Table for entity is created");
    return true;
  }

  /**
   * Drops table with employee phone.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_EMPLOYEE_PHONE);
    try {
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info("Table for entity is dropped");
    return true;
  }
}
