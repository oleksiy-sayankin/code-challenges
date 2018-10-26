package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'employee' controller.
 * Contains basic CRUD operations for table 'employee'.
 */

public class EmployeeController extends AbstractController<Employee, Integer> {
  private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
  private static final String INSERT_EMPLOYEE = "INSERT INTO employee(first_name, last_name, age, department_id, country_id, salary) VALUES (?, ?, ?, ?, ?, ?)";
  private static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
  private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";
  private static final String SELECT_ID_BY_EMPLOYEE = "SELECT id FROM employee WHERE first_name = ? AND last_name = ? AND age = ? AND department_id = ? AND country_id = ?";
  private static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE employee SET first_name = ?, last_name = ?, age = ?, department_id = ?, country_id = ?, salary = ? WHERE id = ?";
  private static final String CREATE_EMPLOYEE = "CREATE TABLE employee (id INT AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(100), last_name VARCHAR(100), age INT, department_id INT, country_id INT, salary FLOAT)";
  private static final String DROP_EMPLOYEE = "DROP TABLE IF EXISTS employee";
  private static final EmployeeController EMPLOYEE_CONTROLLER = new EmployeeController();
  private static final Logger LOG = LogManager.getLogger(EmployeeController.class);
  private EmployeeController() {}

  /**
   * Gets Employee Controller
   *
   * @return Employee Controller
   */
  public static EmployeeController getEmployeeController() {
    return EMPLOYEE_CONTROLLER;
  }

  /**
   * Returns all employees as list. Executes SELECT * FROM employee.
   *
   * @return list of all elements
   */
  @Override public List<Employee> getAll() {
    List<Employee> employees = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_EMPLOYEES);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Employee employee = new Employee();
        employee.setId(rs.getInt(1));
        employee.setFirstName(rs.getString(2));
        employee.setLastName(rs.getString(3));
        employee.setAge(rs.getInt(4));
        employee.setDepartmentId(rs.getInt(5));
        employee.setCountryId(rs.getInt(6));
        employee.setSalary(rs.getFloat(7));
        employees.add(employee);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return employees;
  }

  /**
   * Updates an employee.
   *
   * @param entity employee to update.
   */
  @Override public boolean update(Employee entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_EMPLOYEE_BY_ID);
    try {
      ps.setString(1, entity.getFirstName());
      ps.setString(2, entity.getLastName());
      ps.setInt(3, entity.getAge());
      ps.setInt(4, entity.getDepartmentId());
      ps.setInt(5, entity.getCountryId());
      ps.setFloat(6, entity.getSalary());
      ps.setInt(7, entity.getId());
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
   * Returns employee by it's id, usually primary key.
   *
   * @param id Id of employee
   * @return result employee.
   */
  @Override public Employee getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_EMPLOYEE_BY_ID);
    Employee employee = new Employee();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        employee.setId(rs.getInt(1));
        employee.setFirstName(rs.getString(2));
        employee.setLastName(rs.getString(3));
        employee.setAge(rs.getInt(4));
        employee.setDepartmentId(rs.getInt(5));
        employee.setCountryId(rs.getInt(6));
        employee.setSalary(rs.getFloat(7));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    LOG.info(String.format("Found entity %s.", employee));
    return employee;
  }

  /**
   * Returns id of the entity by its value
   *
   * @param entity entity with filled in data
   * @return id of the entity by its value
   */
  @Override public Integer getIdByEntity(Employee entity) {
    PreparedStatement ps = getPrepareStatement(SELECT_ID_BY_EMPLOYEE);
    Integer id = null;
    try {
      ps.setString(1, entity.getFirstName());
      ps.setString(2, entity.getLastName());
      ps.setInt(3, entity.getAge());
      ps.setInt(4, entity.getDepartmentId());
      ps.setInt(5, entity.getCountryId());
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
   * Deletes employee by id.
   *
   * @param id Id of employee to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_EMPLOYEE);
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
   * Inserts new employee.
   *
   * @param entity employee to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(Employee entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_EMPLOYEE);
    try {
      ps.setString(1, entity.getFirstName());
      ps.setString(2, entity.getLastName());
      ps.setInt(3, entity.getAge());
      ps.setInt(4, entity.getDepartmentId());
      ps.setInt(5, entity.getCountryId());
      ps.setFloat(6, entity.getSalary());
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
   * Creates table for employee.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_EMPLOYEE);
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
   * Drops table with employee.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_EMPLOYEE);
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
