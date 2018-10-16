package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;
import net.javacogito.jdbcconnector.controller.EmployeePhoneController;

import java.io.Serializable;
import java.util.Objects;

/**
 * Basic lass for 'employee_phone' table. Represents a single row.
 */

public final class EmployeePhone implements Serializable, Entity {
  private int id;
  private int employeeId;
  private String number;

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
   * Gets phone number.
   *
   * @return phone number
   */
  public String getNumber() {
    return number;
  }

  /**
   * Sets phone number.
   *
   * @param number phone number
   */
  public void setNumber(String number) {
    this.number = number;
  }

  /**
   * Gets employee phone controller.
   *
   * @return employee phone controller
   */
  @Override public Controller getController() {
    return new EmployeePhoneController();
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    EmployeePhone that = (EmployeePhone) o;
    return id == that.id && employeeId == that.employeeId && Objects.equals(number, that.number);
  }

  @Override public int hashCode() {
    return Objects.hash(id, employeeId, number);
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
    sb.append("number=");
    sb.append(number);
    sb.append("]");
    return sb.toString();
  }
}
