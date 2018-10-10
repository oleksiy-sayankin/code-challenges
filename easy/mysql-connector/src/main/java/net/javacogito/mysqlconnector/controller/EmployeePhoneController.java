package net.javacogito.mysqlconnector.controller;

import net.javacogito.mysqlconnector.entity.EmployeePhone;

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
  public static final String SELECT_ALL_EMPLOYEE_PHONES = "SELECT * FROM employee_phone";
  public static final String INSERT_EMPLOYEE_PHONE = "INSERT INTO employee_phone VALUES (?, ?, ?)";
  public static final String DELETE_EMPLOYEE_PHONE = "DELETE FROM employee_phone WHERE id = ?";
  public static final String SELECT_EMPLOYEE_PHONE_BY_ID = "SELECT * FROM employee_phone WHERE id = ?";
  public static final String UPDATE_EMPLOYEE_PHONE_BY_ID = "UPDATE employee_phone SET employee_id = ?, number = ? WHERE id = ?";
  public static final String CREATE_EMPLOYEE_PHONE = "CREATE TABLE employee_phone (id INT PRIMARY KEY, employee_id INT, number VARCHAR(100))";
  public static final String DROP_EMPLOYEE_PHONE = "DROP TABLE IF EXISTS employee_phone";

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
    return employeePhone;
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
      ps.setInt(1, entity.getId());
      ps.setInt(2, entity.getEmployeeId());
      ps.setString(3, entity.getNumber());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
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
    return true;
  }
}
