package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.EmployeeEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'employee_email' controller.
 * Contains basic CRUD operations for table 'employee_email'.
 */

public class EmployeeEmailController extends AbstractController<EmployeeEmail, Integer> {
  private static final String SELECT_ALL_EMPLOYEE_EMAIL = "SELECT * FROM employee_email";
  private static final String INSERT_EMPLOYEE_EMAIL = "INSERT INTO employee_email(employee_id, email) VALUES (?, ?)";
  private static final String DELETE_EMPLOYEE_EMAIL = "DELETE FROM employee_email WHERE id = ?";
  private static final String SELECT_EMPLOYEE_EMAIL_BY_ID = "SELECT * FROM employee_email WHERE id = ?";
  private static final String SELECT_ID_BY_EMPLOYEE_EMAIL = "SELECT id FROM employee_email WHERE email = ?";
  private static final String UPDATE_EMPLOYEE_EMAIL_BY_ID = "UPDATE employee_email SET employee_id = ?, email = ? WHERE id = ?";
  private static final String CREATE_EMPLOYEE_EMAIL = "CREATE TABLE employee_email (id INT AUTO_INCREMENT PRIMARY KEY, employee_id INT, email VARCHAR(100))";
  private static final String DROP_EMPLOYEE_EMAIL = "DROP TABLE IF EXISTS employee_email";
  private static final EmployeeEmailController EMPLOYEE_EMAIL_CONTROLLER = new EmployeeEmailController();
  private static final Logger LOG = LogManager.getLogger(EmployeeEmailController.class);
  private EmployeeEmailController() {}

  /**
   * Gets Employee Email Controller
   *
   * @return Employee Email Controller
   */
  public static EmployeeEmailController getEmployeeEmailController() {
    return EMPLOYEE_EMAIL_CONTROLLER;
  }

  /**
   * Returns all employee mails as list. Executes SELECT * FROM employee_email.
   *
   * @return list of all elements
   */
  @Override public List<EmployeeEmail> getAll() {
    List<EmployeeEmail> employeeEmails = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_EMPLOYEE_EMAIL);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        EmployeeEmail employeeEmail = new EmployeeEmail();
        employeeEmail.setId(rs.getInt(1));
        employeeEmail.setEmployeeId(rs.getInt(2));
        employeeEmail.setEmail(rs.getString(3));
        employeeEmails.add(employeeEmail);
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    return employeeEmails;
  }

  /**
   * Updates an employee mail.
   *
   * @param entity employee mail to update.
   */
  @Override public Integer update(EmployeeEmail entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_EMPLOYEE_EMAIL_BY_ID);
    try {
      ps.setInt(1, entity.getEmployeeId());
      ps.setString(2, entity.getEmail());
      ps.setInt(3, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      LOG.error(e);
      return null;
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Entity with id = %d updated to new value %s.", entity.getId(), entity));
    return entity.getId();
  }

  /**
   * Returns employee mail by it's id, usually primary key.
   *
   * @param id Id of employee mail
   * @return result employee mail.
   */
  @Override public EmployeeEmail getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_EMPLOYEE_EMAIL_BY_ID);
    EmployeeEmail employeeEmail = new EmployeeEmail();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        employeeEmail.setId(rs.getInt(1));
        employeeEmail.setEmployeeId(rs.getInt(2));
        employeeEmail.setEmail(rs.getString(3));
      }
    } catch (SQLException e) {
      LOG.error(e);
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found entity %s.", employeeEmail));
    return employeeEmail;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @Override public Integer getIdByEntity(EmployeeEmail entity) {
    PreparedStatement ps = getPrepareStatement(SELECT_ID_BY_EMPLOYEE_EMAIL);
    Integer id = null;
    try {
      ps.setString(1, entity.getEmail());
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
   * Deletes employee mail by id.
   *
   * @param id Id of employee mail to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_EMPLOYEE_EMAIL);
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
   * Inserts new employee mail.
   *
   * @param entity employee mail to create.
   * @return true of creation completes successfully.
   */
  @Override public Integer insert(EmployeeEmail entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_EMPLOYEE_EMAIL);
    try {
      ps.setInt(1, entity.getEmployeeId());
      ps.setString(2, entity.getEmail());
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
   * Creates table for employee mail.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_EMPLOYEE_EMAIL);
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
   * Drops table with employee mail.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_EMPLOYEE_EMAIL);
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
