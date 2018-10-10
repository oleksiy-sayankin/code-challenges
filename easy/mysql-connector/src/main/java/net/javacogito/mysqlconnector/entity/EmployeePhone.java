package net.javacogito.mysqlconnector.entity;

import java.util.Objects;

/**
 * Basic lass for 'employee_phone' table. Represents a single row.
 */

public final class EmployeePhone {
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
    sb.append(employeeId);
    sb.append(", ");
    sb.append("number=");
    sb.append(number);
    sb.append("]");
    return sb.toString();
  }

}
