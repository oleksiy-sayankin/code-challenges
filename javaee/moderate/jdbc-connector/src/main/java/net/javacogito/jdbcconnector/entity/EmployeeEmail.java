package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;
import net.javacogito.jdbcconnector.controller.EmployeeEmailController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'employee_email' table. Represents a single row.
 */

public final class EmployeeEmail implements Serializable, Entity {
  private int id;
  private int employeeId;
  private String email;
  private static final Controller CONTROLLER = EmployeeEmailController.getEmployeeEmailController();

  /**
   * Gets id.
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   * @param id primary key
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets employee Id.
   *
   * @return employee Id
   */
  public int getEmployeeId() {
    return employeeId;
  }

  /**
   * Sets employee Id.
   *
   * @param employeeId employee Id
   */
  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  /**
   * Gets employee email.
   *
   * @return employee email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets employee email.
   *
   * @param email employee email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets employee email controller.
   *
   * @return employee email controller.
   */
  @Override public Controller getController() {
    return CONTROLLER;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    EmployeeEmail that = (EmployeeEmail) o;
    return id == that.id && employeeId == that.employeeId && Objects.equals(email, that.email);
  }

  @Override public int hashCode() {
    return Objects.hash(id, employeeId, email);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("employeeId=");
    sb.append(Integer.toString(employeeId));
    sb.append(", ");
    sb.append("email=");
    sb.append(email);
    sb.append("]");
    return sb.toString();
  }
}
