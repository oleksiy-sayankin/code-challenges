package net.javacogito.mysqlconnector.util;

import net.javacogito.mysqlconnector.entity.*;

/**
 * Class for creation an entity instances.
 * This class does not creates entity in DB.
 */
public final class EntityUtil {
  private EntityUtil(){}

  /**
   * Creates country entity.
   *
   * @param id country id
   * @param name country name
   * @return country entity
   */
  public static Country createCountry(int id, String name){
    Country country = new Country();
    country.setId(id);
    country.setName(name);
    return country;
  }

  /**
   * Creates customer entity.
   *
   * @param id customer id
   * @param company customer company
   * @param address customer address
   * @param countryId customer country id
   * @return customer entity
   */
  public static Customer createCustomer(int id, String company, String address, int countryId){
    Customer customer = new Customer();
    customer.setId(id);
    customer.setCompany(company);
    customer.setAddress(address);
    customer.setCountryId(countryId);
    return customer;
  }

  /**
   * Creates department entity.
   *
   * @param id department id
   * @param name department name
   * @return department entity
   */
  public static Department createDepartment(int id, String name){
    Department department = new Department();
    department.setId(id);
    department.setName(name);
    return department;
  }

  /**
   * Creates employee entity.
   *
   * @param id employee id
   * @param firstName employee first name
   * @param lastName employee last name
   * @param age employee age
   * @param departmentId employee department Id
   * @param countryId employee country Id
   * @param salary employee salary
   * @return employee entity
   */

  public static Employee createEmployee(int id, String firstName, String lastName, int age, int departmentId, int countryId, float salary){
    Employee employee = new Employee();
    employee.setId(id);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setAge(age);
    employee.setDepartmentId(departmentId);
    employee.setCountryId(countryId);
    employee.setSalary(salary);
    return employee;
  }

  /**
   * Creates employee email entity.
   *
   * @param id employee email id
   * @param employeeId employee id
   * @param email email value
   * @return employee email entity
   */
  public static EmployeeEmail createEmployeeEmail(int id, int employeeId, String email){
    EmployeeEmail employeeEmail = new EmployeeEmail();
    employeeEmail.setId(id);
    employeeEmail.setEmployeeId(employeeId);
    employeeEmail.setEmail(email);
    return employeeEmail;
  }
}
