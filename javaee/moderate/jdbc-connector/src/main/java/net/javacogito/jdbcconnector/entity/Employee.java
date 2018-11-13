package net.javacogito.jdbcconnector.entity;

import net.javacogito.jdbcconnector.controller.Controller;
import net.javacogito.jdbcconnector.controller.EmployeeController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Basic lass for 'employee' table. Represents a single row.
 */

public final class Employee implements Serializable, Entity {
  private int id;
  private String firstName;
  private String lastName;
  private int age;
  private Department department;
  private Country country;
  private List<EmployeeEmail> employeeEmails = new ArrayList<>();
  private List<EmployeePhone> employeePhones = new ArrayList<>();
  private float salary;
  private static final Controller CONTROLLER = EmployeeController.getEmployeeController();

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
   * Gets employee first name.
   *
   * @return employee first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets employee first name.
   *
   * @param firstName employee first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets employee last name.
   *
   * @return employee last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Gets employee last name.
   *
   * @param lastName employee last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets employee age.
   *
   * @return employee age
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets employee age.
   *
   * @param age employee age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Gets employee department Id.
   *
   * @return employee department Id
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Sets employee department Id.
   *
   * @param department employee department Id.
   */
  public void setDepartment(Department department) {
    this.department = department;
  }

  /**
   * Gets employee country Id.
   *
   * @return employee country Id
   */
  public Country getCountry() {
    return country;
  }

  /**
   * Sets employee country Id.
   *
   * @param country employee country Id
   */
  public void setCountry(Country country) {
    this.country = country;
  }

  /**
   * Gets employee salary.
   *
   * @return employee salary.
   */
  public float getSalary() {
    return salary;
  }

  /**
   * Sets employee salary.
   *
   * @param salary employee salary
   */
  public void setSalary(float salary) {
    this.salary = salary;
  }


  /**
   * Gets employee Emails
   *
   * @return list of emails
   */
  public List<EmployeeEmail> getEmployeeEmails() {
    return employeeEmails;
  }

  /**
   * Sets employee Emails
   *
   * @param employeeEmails list of emails
   */
  public void setEmployeeEmails(List<EmployeeEmail> employeeEmails) {
    this.employeeEmails = employeeEmails;
  }

  /**
   * Gets employee phones
   *
   * @return employee phones
   */
  public List<EmployeePhone> getEmployeePhones() {
    return employeePhones;
  }

  /**
   * Sets employee phones
   *
   * @param employeePhones employee phones
   */
  public void setEmployeePhones(List<EmployeePhone> employeePhones) {
    this.employeePhones = employeePhones;
  }

  /**
   * Gets employee controller.
   *
   * @return employee controller.
   */
  @Override public Controller getController() {
    return CONTROLLER;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Employee employee = (Employee) o;
    return age == employee.age && Objects.equals(firstName, employee.firstName) && Objects
        .equals(lastName, employee.lastName);
  }

  @Override public int hashCode() {
    return Objects.hash(id, firstName, lastName, age);
  }

  @Override public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append("id=");
    sb.append(Integer.toString(id));
    sb.append(", ");
    sb.append("first_name=");
    sb.append(firstName);
    sb.append(", ");
    sb.append("last_name=");
    sb.append(lastName);
    sb.append(", ");
    sb.append("age=");
    sb.append(Integer.toString(age));
    sb.append("]");
    return sb.toString();
  }
}
