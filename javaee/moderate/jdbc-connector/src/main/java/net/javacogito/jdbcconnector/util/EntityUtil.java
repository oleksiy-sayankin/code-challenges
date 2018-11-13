package net.javacogito.jdbcconnector.util;

import net.javacogito.jdbcconnector.entity.*;

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
   * Creates country entity.
   *
   * @param name country name
   * @return country entity
   */
  public static Country createCountry(String name){
    return createCountry(0, name);
  }

  /**
   * Creates customer entity.
   *
   * @param id customer id
   * @param company customer company
   * @param address customer address
   * @param country customer country id
   * @return customer entity
   */
  public static Customer createCustomer(int id, String company, String address, Country country){
    Customer customer = new Customer();
    customer.setId(id);
    customer.setCompany(company);
    customer.setAddress(address);
    customer.setCountry(country);
    return customer;
  }

  /**
   * Creates customer entity.
   *
   * @param company customer company
   * @param address customer address
   * @param country customer country id
   * @return customer entity
   */
  public static Customer createCustomer(String company, String address, Country country){
    return createCustomer(0, company, address, country);
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
   * Creates department entity.
   *
   * @param name department name
   * @return department entity
   */
  public static Department createDepartment(String name){
    return createDepartment(0, name);
  }


  /**
   * Creates employee entity.
   *
   * @param id employee id
   * @param firstName employee first name
   * @param lastName employee last name
   * @param age employee age
   * @param department employee department Id
   * @param country employee country Id
   * @param salary employee salary
   * @return employee entity
   */

  public static Employee createEmployee(int id, String firstName, String lastName, int age, Department department, Country country, float salary){
    Employee employee = new Employee();
    employee.setId(id);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setAge(age);
    employee.setDepartment(department);
    employee.setCountry(country);
    employee.setSalary(salary);
    return employee;
  }


  /**
   * Creates employee entity.
   *
   * @param firstName employee first name
   * @param lastName employee last name
   * @param age employee age
   * @param department employee department Id
   * @param country employee country Id
   * @param salary employee salary
   * @return employee entity
   */

  public static Employee createEmployee(String firstName, String lastName, int age, Department department, Country country, float salary){
    return createEmployee(0, firstName, lastName, age, department, country, salary);
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

  /**
   * Creates employee email entity.
   *
   * @param employeeId employee id
   * @param email email value
   * @return employee email entity
   */
  public static EmployeeEmail createEmployeeEmail(int employeeId, String email){
    return createEmployeeEmail(0, employeeId, email);
  }

  /**
   * Creates employee phone entity.
   *
   * @param id employee phone id
   * @param employeeId employee id
   * @param number phone value
   * @return employee phone entity
   */
  public static EmployeePhone createEmployeePhone(int id, int employeeId, String number){
    EmployeePhone employeePhone = new EmployeePhone();
    employeePhone.setId(id);
    employeePhone.setEmployeeId(employeeId);
    employeePhone.setNumber(number);
    return employeePhone;
  }

  /**
   * Creates employee phone entity.
   *
   * @param employeeId employee id
   * @param number phone value
   * @return employee phone entity
   */
  public static EmployeePhone createEmployeePhone(int employeeId, String number){
    return createEmployeePhone(0, employeeId, number);
  }


  /**
   * Creates order entity.
   *
   * @param id order id
   * @param customer customer Id
   * @param product product Id
   * @param amount amount of product
   * @return order entity
   */
  public static Order createOrder(int id, Customer customer, Product product, int amount){
    Order order = new Order();
    order.setId(id);
    order.setCustomer(customer);
    order.setProduct(product);
    order.setAmount(amount);
    return order;
  }

  /**
   * Creates order entity.
   *
   * @param customer customer Id
   * @param product product Id
   * @param amount amount of product
   * @return order entity
   */
  public static Order createOrder(Customer customer, Product product, int amount){
    return createOrder(0, customer, product, amount);
  }

  /**
   * Creates product entity.
   *
   * @param id product Id
   * @param name product name
   * @param productType product type Id
   * @param price product price
   * @return product entity
   */
  public static Product createProduct(int id, String name, ProductType productType, float price){
    Product product = new Product();
    product.setId(id);
    product.setName(name);
    product.setProductType(productType);
    product.setPrice(price);
    return product;
  }

  /**
   * Creates product entity.
   *
   * @param name product name
   * @param productType product type Id
   * @param price product price
   * @return product entity
   */
  public static Product createProduct(String name, ProductType productType, float price){
    return createProduct(0, name, productType, price);
  }

  /**
   * Creates product type entity.
   *
   * @param id product type id
   * @param name product type name
   * @return product type
   */
  public static ProductType createProductType(int id, String name){
    ProductType productType = new ProductType();
    productType.setId(id);
    productType.setName(name);
    return productType;
  }

  /**
   * Creates product type entity.
   *
   * @param name product type name
   * @return product type
   */
  public static ProductType createProductType(String name){
    return createProductType(0, name);
  }
}
