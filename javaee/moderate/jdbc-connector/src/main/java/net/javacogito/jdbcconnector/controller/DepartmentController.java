package net.javacogito.jdbcconnector.controller;

import net.javacogito.jdbcconnector.entity.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation for table 'department' controller.
 * Contains basic CRUD operations for table 'department'.
 */

public class DepartmentController extends AbstractController<Department, Integer> {
  public static final String SELECT_ALL_DEPARTMENTS = "SELECT * FROM department";
  public static final String INSERT_DEPARTMENT = "INSERT INTO department VALUES (?, ?)";
  public static final String DELETE_DEPARTMENT = "DELETE FROM department WHERE id = ?";
  public static final String SELECT_DEPARTMENT_BY_ID = "SELECT * FROM department WHERE id = ?";
  public static final String UPDATE_DEPARTMENT_BY_ID = "UPDATE department SET name = ? WHERE id = ?";
  public static final String CREATE_DEPARTMENT = "CREATE TABLE department (id INT PRIMARY KEY, name VARCHAR(100))";
  public static final String DROP_DEPARTMENT = "DROP TABLE IF EXISTS department";

  /**
   * Returns all countries as list. Executes SELECT * FROM department.
   *
   * @return list of all elements
   */
  @Override public List<Department> getAll() {
    List<Department> departments = new LinkedList<>();
    PreparedStatement ps = getPrepareStatement(SELECT_ALL_DEPARTMENTS);
    try {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Department department = new Department();
        department.setId(rs.getInt(1));
        department.setName(rs.getString(2));
        departments.add(department);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return departments;
  }

  /**
   * Updates a department.
   *
   * @param entity department to update.
   */
  @Override public boolean update(Department entity) {
    PreparedStatement ps = getPrepareStatement(UPDATE_DEPARTMENT_BY_ID);
    try {
      ps.setString(1, entity.getName());
      ps.setInt(2, entity.getId());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Returns department by it's id, usually primary key.
   *
   * @param id Id of department
   * @return result department.
   */
  @Override public Department getEntityById(Integer id) {
    PreparedStatement ps = getPrepareStatement(SELECT_DEPARTMENT_BY_ID);
    Department department = new Department();
    try {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        department.setId(rs.getInt(1));
        department.setName(rs.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closePrepareStatement(ps);
    }
    return department;
  }

  /**
   * Deletes department by id.
   *
   * @param id Id of department to delete
   * @return true of deletion completes successfully.
   */
  @Override public boolean delete(Integer id) {
    PreparedStatement ps = getPrepareStatement(DELETE_DEPARTMENT);
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
   * Inserts new department.
   *
   * @param entity department to create.
   * @return true of creation completes successfully.
   */
  @Override public boolean insert(Department entity) {
    PreparedStatement ps = getPrepareStatement(INSERT_DEPARTMENT);
    try {
      ps.setInt(1, entity.getId());
      ps.setString(2, entity.getName());
      ps.executeUpdate();
    } catch (SQLException e) {
      return false;
    } finally {
      closePrepareStatement(ps);
    }
    return true;
  }

  /**
   * Creates table for department.
   *
   * @return true if creation is successful
   */
  @Override public boolean create() {
    PreparedStatement ps = getPrepareStatement(CREATE_DEPARTMENT);
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
   * Drops table with department.
   *
   * @return true if deletion is successful
   */
  @Override public boolean drop() {
    PreparedStatement ps = getPrepareStatement(DROP_DEPARTMENT);
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
